package com.offerup.controller.dtos.auctionDTOs;

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
