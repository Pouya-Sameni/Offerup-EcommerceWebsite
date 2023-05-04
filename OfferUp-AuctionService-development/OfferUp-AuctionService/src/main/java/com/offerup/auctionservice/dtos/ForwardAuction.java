package com.offerup.auctionservice.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@Document("forwardCollection")
public class ForwardAuction extends Auction{

    private String highestBidderUserId;
    private double highestBid;




    // constructor that takes string parameters for start/end times
    public ForwardAuction(ObjectId auctionId, String itemId, AuctionType auctionType, double initialPrice,
                        String startTimeOfAuction, String endTimeOfAuction, boolean auctionEnded
            , String highestBidderUserId, double currentPrice, double highestBid, String soldToUserId) {
        super(auctionId, itemId, auctionType,initialPrice,currentPrice, startTimeOfAuction, endTimeOfAuction, auctionEnded, soldToUserId);
        this.highestBidderUserId = highestBidderUserId;
        this.highestBid = highestBid;
    }



}
