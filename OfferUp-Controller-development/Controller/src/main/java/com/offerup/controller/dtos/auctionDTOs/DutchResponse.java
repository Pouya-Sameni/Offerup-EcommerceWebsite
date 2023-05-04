package com.offerup.controller.dtos.auctionDTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DutchResponse {
    private DutchAuction auction;
    private String message;

    public DutchResponse(DutchAuction auction) {
        this.auction = auction;
        this.message = "Auction found";

    }

    public DutchResponse(String message) {
        this.message = message;
    }

}
