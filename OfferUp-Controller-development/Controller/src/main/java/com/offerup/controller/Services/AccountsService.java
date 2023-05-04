package com.offerup.controller.Services;


import com.offerup.controller.dtos.accountsDTOs.LoginCredentials;
import com.offerup.controller.dtos.accountsDTOs.TokenClaim;
import com.offerup.controller.dtos.accountsDTOs.UserDto;
import com.offerup.controller.dtos.auctionDTOs.ForwardResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@Service
public class AccountsService {

    private RestTemplate restTemplate;

    @Value("${accountService}")
    private String API_URL;

    public AccountsService ()
    {
        restTemplate = new RestTemplate();
    }


    public String loginUser (LoginCredentials loginCredentials) throws AccessDeniedException {
        try{
            String loginUrl = API_URL + "login";
            String response = restTemplate.postForObject(loginUrl, loginCredentials, String.class);
            return response;

        }catch (Exception e)
        {
            throw new AccessDeniedException("cant login");
        }

    }

    public String registerUser (UserDto userDto) throws AccessDeniedException {
        String loginUrl = API_URL + "signup";
        String response = restTemplate.postForObject(loginUrl, userDto, String.class);

        if (response.toLowerCase().contains("incorrect credentials"))
        {
            throw new AccessDeniedException("Incorrect Login");
        }
        return response;
    }

    public TokenClaim getUserClaims (String token) throws AccessDeniedException {
        String loginUrl = API_URL + "validate";
        ResponseEntity<TokenClaim> response = restTemplate.postForEntity(loginUrl, token, TokenClaim.class);
        int statusCode = response.getStatusCodeValue();
        TokenClaim claim = response.getBody();


        if (statusCode != HttpStatus.OK.value())
        {
            throw new AccessDeniedException("Incorrect Login");
        }
        return claim;
    }

    public UserDto getUserDetails (String token) throws NoSuchElementException {
        try{
            TokenClaim claim = getUserClaims(token);
            String loginUrl = String.format(API_URL + "getdetails?userId=%s", claim.getUserId());
            ResponseEntity<UserDto> response = restTemplate.getForEntity(loginUrl, UserDto.class);

            return response.getBody();

        }catch (Exception e)
        {

            return null;
        }

    }

}
