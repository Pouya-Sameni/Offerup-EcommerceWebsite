package com.offerup.controller.dtos.auctionDTOs;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class ForwardAuction extends Auction{

    private String highestBidderUserId;
    private double highestBid;




    // constructor that takes string parameters for start/end times
    public ForwardAuction(ObjectId auctionId, String itemId, AuctionType auctionType, double initialPrice,
                        String startTimeOfAuction, String endTimeOfAuction, boolean auctionEnded
            , String highestBidderUserId, double currentPrice, double highestBid, String soldToUserId) {
        super(auctionId, itemId, auctionType, currentPrice, initialPrice, startTimeOfAuction, endTimeOfAuction, auctionEnded, soldToUserId);
        this.highestBidderUserId = highestBidderUserId;
        this.highestBid = highestBid;
    }

    public Date DateStartTime () throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd-HH:mm");

        Date date = formatter.parse(this.getStartTimeOfAuction());
        return date;
    }
    public Date DateEndTime () throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd-HH:mm");

        Date date = formatter.parse(this.getEndTimeOfAuction());
        return date;
    }

}
