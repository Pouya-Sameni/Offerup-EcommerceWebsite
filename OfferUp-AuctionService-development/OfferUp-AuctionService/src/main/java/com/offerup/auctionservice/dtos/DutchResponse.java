package com.offerup.auctionservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
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
