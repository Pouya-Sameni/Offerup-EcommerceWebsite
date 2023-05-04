package com.offerup.AccountsService.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Password {
    private String password;
    private String salt;


}
