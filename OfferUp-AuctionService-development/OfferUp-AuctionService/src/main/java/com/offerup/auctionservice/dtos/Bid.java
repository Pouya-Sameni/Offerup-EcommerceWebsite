package com.offerup.auctionservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;


@Getter
@Setter
@AllArgsConstructor
public class Bid {
    private String auctionId;
    private double bidAmount;
    private String userId;


}
