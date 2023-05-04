package com.offerup.AccountsService.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class TokenClaim {

    private String message;
    private String userId;
    private String username;


}
