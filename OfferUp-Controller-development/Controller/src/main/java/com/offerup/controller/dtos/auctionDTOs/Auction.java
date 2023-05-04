package com.offerup.controller.dtos.auctionDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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


}
