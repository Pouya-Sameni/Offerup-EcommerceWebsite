package com.offerup.AccountsService.Services;

import com.offerup.AccountsService.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
public class LoginService {


    private QueryService queryService;
    private ModificationService modificationService;
    private TokenService tokenService;
    @Autowired
    public LoginService (TokenService tokenService, QueryService queryService, ModificationService modificationService){
        this.tokenService = tokenService;
        this.queryService = queryService;
        this.modificationService = modificationService;
    }


    public String login(LoginCredentials loginCredentials) throws AccessDeniedException {
        // retrieve the user from the database based on the email
        UserEntity userEntity = queryService.findUserByUsername(loginCredentials.getUsername());
        // check if the user exists and the password is correct
        if (userEntity == null || !tokenService.checkPassword(userEntity, loginCredentials.getPassword())) {
            throw new AccessDeniedException("Invalid email or password");
        }

        String token =tokenService.createSessionToken(userEntity);
        // generate a JWT token

        return token;
    }

    public String register(UserDto userDto) throws AccessDeniedException {

        if (queryService.findUserByUsername(userDto.getUsername()) != null &&
                queryService.findUserByEmail(userDto.getEmail()) != null)
        {
            throw new AccessDeniedException("User Exists");
        }
        Password password = tokenService.hashPassword(userDto.getPassword());
        UserEntity userEntity = new UserEntity(
                null,
                userDto.getEmail(),
                new Password(password.getPassword(), password.getSalt()),
                userDto.getUsername(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getAddress()
        );
        modificationService.insertUser(userEntity);

        String token = login(new LoginCredentials(userDto.getUsername(), userDto.getPassword() ));

        return token;
    }

    public TokenClaim validate(String token) throws AccessDeniedException {
        // retrieve the user from the database based on the email
        TokenClaim claim = new TokenClaim("User Not Found", null, null);
        try{
            claim =tokenService.parseSessionToken(token);
        }catch (Exception e)
        {
            throw new AccessDeniedException("Invalid Token");
        }
        return claim;
    }


}
