package com.offerup.auctionservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Buy {
    private String auctionId;
    private String userId;

}
