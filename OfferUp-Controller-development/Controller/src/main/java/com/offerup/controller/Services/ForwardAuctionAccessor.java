package com.offerup.controller.Services;

import com.offerup.controller.dtos.auctionDTOs.*;
import com.offerup.controller.interfaces.AuctionAccessors;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@Component
public class ForwardAuctionAccessor implements AuctionAccessors {

    //private String API_URL = "http://172.20.0.3:8082/api/auction/";
    private String API_URL = "http://localhost:8082/api/auction/";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean isUserWinner(String auctionId, String userid) {
        try {

            String url = String.format(API_URL + "forward/user/winner?auctionId=%s&userId=%s", auctionId, userid);
            System.out.println(url);
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    @Override
    public ForwardAuction auctionDetails(String auctionId) {
        try {
            String url = String.format(API_URL + "forward/search/id?auctionId=%s", auctionId);
            System.out.println(url);

            ResponseEntity<ForwardResponse> response = restTemplate.getForEntity(url, ForwardResponse.class);
            ForwardResponse responseBody = response.getBody();
            return responseBody.getAuction();

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
