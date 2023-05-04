package com.offerup.CatalogueService.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Shipping {
    private double fee;
    private int minDays;
    private int maxDays;
    private ShippingType type;
}

