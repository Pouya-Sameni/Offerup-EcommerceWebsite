package com.offerup.auctionservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Document("dutchCollection")
public class DutchAuction extends Auction {

    private double minimumPrice;
    private int decrement;

    public DutchAuction (ObjectId auctionId, String itemId, AuctionType auctionType, double initialPrice,
                      String startTimeOfAuction, String endTimeOfAuction, boolean auctionEnded,
                      double currentPrice, double minimumPrice, int decrement, String soldToUserId) {
        super(auctionId, itemId, auctionType, initialPrice, currentPrice, startTimeOfAuction, endTimeOfAuction, auctionEnded, soldToUserId);
        this.minimumPrice = minimumPrice;
        this.decrement = decrement;
    }



}
