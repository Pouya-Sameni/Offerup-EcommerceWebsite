package com.offerup.controller.Services;

import com.offerup.controller.dtos.auctionDTOs.Auction;
import com.offerup.controller.dtos.auctionDTOs.AuctionType;
import com.offerup.controller.interfaces.AuctionAccessors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AuctionAccessorFactory {


    public static AuctionAccessors createAuctionAccess(AuctionType type) {
        if (type.equals(AuctionType.DUTCH)) {
            return new DutchAuctionAccessor();
        } else if (type.equals(AuctionType.FORWARD)) {
            return new ForwardAuctionAccessor();
        }
        return null;
    }


}