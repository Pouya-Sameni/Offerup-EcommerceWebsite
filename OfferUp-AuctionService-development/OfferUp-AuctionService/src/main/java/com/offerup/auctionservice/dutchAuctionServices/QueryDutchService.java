package com.offerup.auctionservice.dutchAuctionServices;


import com.offerup.auctionservice.dtos.DutchAuction;
import com.offerup.auctionservice.dtos.ForwardAuction;
import com.offerup.auctionservice.repos.DutchAuctionDB;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class QueryDutchService {

    DutchAuctionDB dutchAuctionDB;



    @Autowired
    public QueryDutchService (DutchAuctionDB dutchAuctionDB){
        this.dutchAuctionDB = dutchAuctionDB;
    }

    public List<DutchAuction> getAllAuctions (){
        return dutchAuctionDB.findAll();
    }


    public List<DutchAuction> getAllOpen (){
        return dutchAuctionDB.findAllByAuctionEnded(false);
    }

    public DutchAuction auctionDetails (String id)  {

            ObjectId newId = new ObjectId(id);
            DutchAuction auction = dutchAuctionDB.findByAuctionId(newId);
            if (auction == null)
            {
                throw new InputMismatchException("Auction Not Found");
            }
            return auction;

    }

    public boolean isUserWinner (String auctionId, String userId)
    {
        try{
            DutchAuction dutchAuction = auctionDetails(auctionId);
            if (dutchAuction.isAuctionEnded() && dutchAuction.getSoldToUserId().equals(userId))
            {
                return true;
            }
            return false;

        }catch (Exception e)
        {
            return false;
        }

    }
    public List<DutchAuction> searchByItems (List<String> ids) throws NoSuchElementException {

        List<DutchAuction> auctions = new ArrayList<>();

        for (String id: ids)
        {

            List<DutchAuction> dutchAuctions = dutchAuctionDB.findAllItemByItemIdAndAuctionEndedIsFalse(id);

            if (!dutchAuctions.isEmpty() && dutchAuctions != null)
            {
                auctions.addAll(dutchAuctions);
            }
        }



        if (auctions == null || auctions.isEmpty())
        {
            throw new NoSuchElementException("no auctions found");
        }



        if (auctions == null)
        {
            throw new NoSuchElementException("no auctions found");
        }

        return auctions;

    }

}
