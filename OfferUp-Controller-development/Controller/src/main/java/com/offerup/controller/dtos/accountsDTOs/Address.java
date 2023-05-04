package com.offerup.controller.dtos.accountsDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String address;
    private String country;
    private String postalCode;
    private String province;
    private String city;


}
