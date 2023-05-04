package com.offerup.controller.dtos.accountsDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userId;
    private String email;
    private String password;

    private String username;
    private String firstName;
    private String lastName;
    private Address address;

}
