package com.offerup.controller.dtos.accountsDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TokenClaim {

    private String message;
    private String userId;
    private String username;


}
