package com.offerup.auctionservice.dutchAuctionServices;


import com.offerup.auctionservice.dtos.*;
import com.offerup.auctionservice.repos.DutchAuctionDB;
import com.offerup.auctionservice.websockethandlers.DutchWSHandler;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class UpdateDutchService {

    private DutchAuctionDB dutchAuctionDB;
    private QueryDutchService queryDutchService;
    private MongoTemplate mongoTemplate;

    private DutchWSHandler dutchWSHandler;
    @Autowired
    public UpdateDutchService (DutchAuctionDB dutchAuctionDB, QueryDutchService queryDutchService,
                               MongoTemplate mongoTemplate, DutchWSHandler dutchWSHandler){
        this.dutchAuctionDB = dutchAuctionDB;
        this .queryDutchService = queryDutchService;
        this.mongoTemplate = mongoTemplate;
        this.dutchWSHandler = dutchWSHandler;
    }

    public void reset()
    {
        List<DutchAuction> auctionList = dutchAuctionDB.findAll();

        for (DutchAuction auction: auctionList)
        {
            updateField(auction.getAuctionId().toString(), "currentPrice", auction.getInitialPrice());
            updateField(auction.getAuctionId().toString(), "soldToUserId", "");
            updateField(auction.getAuctionId().toString(), "auctionEnded", false);
        }
    }

    private void updateField(String id, String fieldName, Object value) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));

        Update update = new Update().set(fieldName, value);

        mongoTemplate.updateFirst(query, update, DutchAuction.class);
        try {
            this.dutchWSHandler.broadcast(id, this.queryDutchService.auctionDetails(id));
        }catch (Exception e)
        {
            System.out.println("Unable to Send Updates");
        }

    }

    public DutchAuction decrement (String auctionId)
    {
        try{
            DutchAuction auction = queryDutchService.auctionDetails(auctionId);
            double newPrice = 0;
            if (auction.getCurrentPrice() - auction.getDecrement() >= auction.getMinimumPrice() )
            {
                 newPrice = auction.getCurrentPrice() - auction.getDecrement();

            }
            else {
                 newPrice = auction.getMinimumPrice();
            }
            updateField(auctionId, "currentPrice", newPrice);
            auction = queryDutchService.auctionDetails(auctionId);

            return auction;
        }catch (Exception e)
        {
            System.out.println("Unable to decrement auction given");
        }

        return null;
    }

    public DutchAuction closeAuction (String auctionId)
    {
        try {
            updateField(auctionId, "auctionEnded", true);

            DutchAuction auction = queryDutchService.auctionDetails(auctionId);
            return auction;

        }catch (Exception e )
        {
            System.out.println("Unable to close auction");
        }

        return null;
    }

    public DutchAuction buyAuction (String auctionId, String userId) throws AccessDeniedException {

            DutchAuction auction = dutchAuctionDB.findByAuctionId(new ObjectId(auctionId));

        if (!auction.isAuctionEnded())
        {
            updateField(auctionId, "soldToUserId", userId);
            closeAuction(auctionId);
            auction = queryDutchService.auctionDetails(auctionId);
            return auction;
        }
        throw new AccessDeniedException("could not buy");

    }

}
