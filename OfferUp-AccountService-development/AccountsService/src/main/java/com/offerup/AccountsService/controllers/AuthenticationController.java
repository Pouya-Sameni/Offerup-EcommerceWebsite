package com.offerup.AccountsService.controllers;


import com.offerup.AccountsService.Services.QueryService;
import com.offerup.AccountsService.dtos.LoginCredentials;
import com.offerup.AccountsService.dtos.TokenClaim;
import com.offerup.AccountsService.dtos.UserDto;
import com.offerup.AccountsService.dtos.UserEntity;
import com.offerup.AccountsService.Services.LoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {

    private LoginService loginService;
    private QueryService queryService;

    @Autowired
    public AuthenticationController(LoginService loginService, QueryService queryService)
    {
        this.loginService = loginService;
        this.queryService = queryService;
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginCredentials loginCredentials, HttpServletResponse response) {

        try {
            String token = loginService.login(loginCredentials);

            // Return the response entity
            return new ResponseEntity<String>(token, HttpStatus.OK);

        } catch (AccessDeniedException e) {
            return new ResponseEntity<String>("Incorrect Credentials", HttpStatus.UNAUTHORIZED);
        }

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody UserDto userDto, HttpServletResponse response) {

        try{
            String token = loginService.register(userDto);


            return new ResponseEntity<String>(token,HttpStatus.OK);

        } catch (AccessDeniedException e)
        {
            return new ResponseEntity<String>("Unable To SignUp",HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/validate")
    @CrossOrigin(origins = "*")
    public ResponseEntity<TokenClaim> validateUser(@RequestBody String token) {

        try{

            TokenClaim claim = loginService.validate(token);
            return new ResponseEntity<>(claim,HttpStatus.OK);

        } catch (AccessDeniedException e)
        {
            return new ResponseEntity<TokenClaim>(new TokenClaim("No User Found", null, null),HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/getdetails")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UserDto> getUserDetails(@RequestParam ("userId") String userId) {

        try{

            UserDto user = queryService.findUserById(userId);

            return new ResponseEntity<>(user,HttpStatus.OK);

        } catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }
}
