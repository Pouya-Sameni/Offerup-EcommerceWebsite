package com.offerup.controller.dtos.auctionDTOs;

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
