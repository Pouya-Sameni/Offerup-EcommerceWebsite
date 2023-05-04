package com.offerup.auctionservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ForwardResponse {
    private ForwardAuction auction;
    private String message;

    public ForwardResponse(ForwardAuction auction) {
        this.auction = auction;
        this.message = "Auction found";

    }

    public ForwardResponse(String message) {
        this.message = message;
    }

}
