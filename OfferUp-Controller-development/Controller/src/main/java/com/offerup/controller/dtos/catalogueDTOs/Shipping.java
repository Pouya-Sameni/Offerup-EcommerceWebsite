package com.offerup.controller.dtos.catalogueDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {
    private double fee;
    private int minDays;
    private int maxDays;
    private ShippingType type;
}

