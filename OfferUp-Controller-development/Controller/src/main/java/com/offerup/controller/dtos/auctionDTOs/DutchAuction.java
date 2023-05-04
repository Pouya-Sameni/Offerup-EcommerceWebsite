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
public class DutchAuction extends Auction {

    private double minimumPrice;
    private int decrement;

    public DutchAuction (ObjectId auctionId, String itemId, AuctionType auctionType, double initialPrice,
                      String startTimeOfAuction, double currentPrice, String endTimeOfAuction, boolean auctionEnded, double minimumPrice, int decrement, String soldToUserId) {
        //super(auctionId, itemId, auctionType, initialPrice, currentPrice, startTimeOfAuction, endTimeOfAuction, auctionEnded, soldToUserId);
        super(auctionId, itemId, auctionType, initialPrice, currentPrice, startTimeOfAuction, endTimeOfAuction, auctionEnded, soldToUserId);
        this.minimumPrice = minimumPrice;
        this.decrement = decrement;
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
