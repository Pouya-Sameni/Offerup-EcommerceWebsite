package com.offerup.AccountsService.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
public class Address {
    private String address;
    private String country;
    private String postalCode;
    private String province;
    private String city;


}
