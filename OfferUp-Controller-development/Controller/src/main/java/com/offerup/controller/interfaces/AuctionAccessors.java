package com.offerup.controller.interfaces;

import com.offerup.controller.dtos.auctionDTOs.Auction;
import com.offerup.controller.dtos.auctionDTOs.AuctionType;

public interface AuctionAccessors {
    boolean isUserWinner(String auctionId, String userid);

    Auction auctionDetails(String auctionId);
}
