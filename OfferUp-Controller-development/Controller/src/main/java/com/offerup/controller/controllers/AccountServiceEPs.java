package com.offerup.controller.controllers;


import com.offerup.controller.Services.AccountsService;
import com.offerup.controller.dtos.accountsDTOs.LoginCredentials;
import com.offerup.controller.dtos.accountsDTOs.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/accounts")
public class AccountServiceEPs {

    @Autowired
    AccountsService accountsService;


    @PostMapping("/signin")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginCredentials loginCredentials, HttpServletResponse response) {

        try {
            String token = accountsService.loginUser(loginCredentials);

            return new ResponseEntity<String>(token, HttpStatus.OK);

        } catch (AccessDeniedException e) {
            return new ResponseEntity<String>("Incorrect Credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        try {
            String token = accountsService.registerUser(userDto);


            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Incorrect Credentials", HttpStatus.NOT_FOUND);
        }
    }


}
