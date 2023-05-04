package com.offerup.auctionservice.forwardAuctionServices;


import com.offerup.auctionservice.dtos.DutchAuction;
import com.offerup.auctionservice.dtos.ForwardAuction;
import com.offerup.auctionservice.repos.DutchAuctionDB;
import com.offerup.auctionservice.repos.ForwardAuctionDB;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QueryForwardService {


    ForwardAuctionDB forwardAuctionDB;
    MongoTemplate mongoTemplate;


    @Autowired
    public QueryForwardService (ForwardAuctionDB forwardAuctionDB, MongoTemplate mongoTemplate){
        this.forwardAuctionDB = forwardAuctionDB;
        this.mongoTemplate = mongoTemplate;
    }

    public List<ForwardAuction> getAllAuctions () throws ParseException {

        List<ForwardAuction> result = forwardAuctionDB.findAll();
        return result;
    }

    public ForwardAuction auctionDetails (String id) throws InvalidAttributeValueException {

            ObjectId newId = new ObjectId(id);
            ForwardAuction auction = forwardAuctionDB.findByAuctionId(newId);

            if (auction == null)
            {
                throw new InvalidAttributeValueException("auction not found");
            }
            return auction;


    }

    public boolean isUserWinner (String auctionId, String userId)
    {
        try{
            ForwardAuction forwardAuction = auctionDetails(auctionId);
            if (forwardAuction.isAuctionEnded() && forwardAuction.getSoldToUserId().equals(userId))
            {
                return true;
            }
            return false;

        }catch (Exception e)
        {
            return false;
        }

    }

    public List<ForwardAuction> searchByItems (List<String> ids) throws NoSuchElementException {

        List<ForwardAuction> auctions = new ArrayList<>();

        for (String id: ids)
        {
            List<ForwardAuction> forwardAuctions = forwardAuctionDB.findAllItemByItemIdAndAuctionEndedIsFalse(id);


            if (!forwardAuctions.isEmpty() && forwardAuctions != null)
            {
                auctions.addAll(forwardAuctions);

            }
        }


        if (auctions == null || auctions.isEmpty())
        {
            throw new NoSuchElementException("no auctions found");
        }

        return auctions;
    }

    public List<ForwardAuction> getAllOpen (){
        return forwardAuctionDB.findAllByAuctionEnded(false);
    }
}
