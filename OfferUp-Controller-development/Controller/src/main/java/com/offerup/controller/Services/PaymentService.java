package com.offerup.controller.Services;


import com.offerup.controller.dtos.auctionDTOs.DutchResponse;
import com.offerup.controller.dtos.paymentDTOs.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PaymentService {
    private RestTemplate restTemplate;


    @Value("${paymentService}")
    private String API_URL;


    @Autowired
    public PaymentService ()
    {
        restTemplate = new RestTemplate();
    }


    public Receipt payForAuction (Map<String, Object> requestBody){
        try{
            String url = String.format(API_URL + "pay");

            ResponseEntity<Receipt> response = restTemplate.postForEntity(url, requestBody, Receipt.class);
            Receipt responseBody = response.getBody();
            return responseBody;

        }catch (Exception e)
        {
            return null;
        }
    }
}
