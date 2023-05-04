package com.offerup.auctionservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@AllArgsConstructor
public class Auction {

    @Id
    private ObjectId auctionId;
    private String itemId;
    private AuctionType auctionType;
    private double initialPrice;
    private double currentPrice;

    private String startTimeOfAuction;

    private String endTimeOfAuction;

    private boolean auctionEnded;

    private String soldToUserId;

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
