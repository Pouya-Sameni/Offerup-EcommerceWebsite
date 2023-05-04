package com.offerup.auctionservice.forwardAuctionServices;


import com.offerup.auctionservice.dtos.Bid;
import com.offerup.auctionservice.dtos.DutchAuction;
import com.offerup.auctionservice.dtos.ForwardAuction;
import com.offerup.auctionservice.repos.ForwardAuctionDB;
import com.offerup.auctionservice.websockethandlers.ForwardWSHandler;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class UpdateForwardService {
    private ForwardAuctionDB forwardAuctionDB;
    private QueryForwardService queryForwardService;
    private MongoTemplate mongoTemplate;
    private ForwardWSHandler forwardWSHandler;
    @Autowired
    public UpdateForwardService (ForwardAuctionDB forwardAuctionDB, QueryForwardService queryForwardService,
                                 MongoTemplate mongoTemplate,
                                 ForwardWSHandler forwardWSHandler){
        this.forwardAuctionDB = forwardAuctionDB;
        this .queryForwardService = queryForwardService;
        this.mongoTemplate = mongoTemplate;
        this.forwardWSHandler = forwardWSHandler;
    }




    private void updateField(String id, String fieldName, Object value) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));

        Update update = new Update().set(fieldName, value);

        mongoTemplate.updateFirst(query, update, ForwardAuction.class);

        try {
            this.forwardWSHandler.broadcast(id, this.queryForwardService.auctionDetails(id));
        }catch (Exception e)
        {
            System.out.println("Unable to Send Updates");
        }

    }

    public ForwardAuction closeAuction (String auctionId)
    {
        try {
            ForwardAuction auction = queryForwardService.auctionDetails(auctionId);

            updateField(auctionId, "auctionEnded", true);
            updateField(auctionId, "soldToUserId", auction.getHighestBidderUserId());


            auction = queryForwardService.auctionDetails(auctionId);
            return auction;

        }catch (Exception e )
        {
            System.out.println("Unable to close auction");
        }

        return null;
    }

    public ForwardAuction updateAuctionBid (Bid bid){

        try {
            ForwardAuction auction = queryForwardService.auctionDetails(bid.getAuctionId());
            if (auction.getHighestBid() >= bid.getBidAmount() || auction.isAuctionEnded())
            {
                throw new IllegalArgumentException("Error with auction");
            }

            updateField(bid.getAuctionId(), "highestBid", bid.getBidAmount());
            updateField(bid.getAuctionId(), "currentPrice", bid.getBidAmount());
            updateField(bid.getAuctionId(), "highestBidderUserId", bid.getUserId());


            auction = forwardAuctionDB.findByAuctionId(new ObjectId(bid.getAuctionId()));
            return auction;

        }catch (Exception e){
            throw new IllegalArgumentException("Invalid auction");
        }


    }

    public void reset(int time)
    {
        List<ForwardAuction> auctionList = forwardAuctionDB.findAll();

        for (ForwardAuction auction: auctionList)
        {
            updateField(auction.getAuctionId().toString(), "currentPrice", auction.getInitialPrice());
            updateField(auction.getAuctionId().toString(), "highestBid", auction.getInitialPrice());
            updateField(auction.getAuctionId().toString(), "soldToUserId", "");
            updateField(auction.getAuctionId().toString(), "highestBidderUserId", "");
            updateField(auction.getAuctionId().toString(), "auctionEnded", false);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime tenMinutesLater = now.plusMinutes(time);
            // Define date and time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");

            // Format date and time to desired format
            String formattedDateTime = now.format(formatter);


            updateField(auction.getAuctionId().toString(), "startTimeOfAuction", formattedDateTime);
            formattedDateTime = tenMinutesLater.format(formatter);
            updateField(auction.getAuctionId().toString(), "endTimeOfAuction", formattedDateTime);



        }
    }

}
