package com.offerup.controller.Services;


import com.offerup.controller.dtos.accountsDTOs.TokenClaim;
import com.offerup.controller.dtos.auctionDTOs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuctionService {


    @Value("${auctionService}")
    private String API_URL;
    private AccountsService accountsService;
    private CatalogueService catalogueService;

    private RestTemplate restTemplate;

    @Autowired
    public AuctionService(AccountsService accountsService,
                          CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
        this.accountsService = accountsService;
        this.restTemplate = new RestTemplate();
    }


    public ForwardAuction bidOnForwardAuction(Bid bid, String token) throws AccessDeniedException {

        try {
            TokenClaim claim = accountsService.getUserClaims(token);
            bid.setUserId(claim.getUsername());

            String bidUrl = API_URL + "forward/bid";
            ResponseEntity<ForwardResponse> response = restTemplate.postForEntity(bidUrl, bid, ForwardResponse.class);
            int statusCode = response.getStatusCodeValue();
            ForwardResponse responseBody = response.getBody();

            return responseBody.getAuction();

        } catch (AccessDeniedException e) {
            throw new AccessDeniedException("user not found");
        } catch (HttpClientErrorException e) {
            throw new NoSuchElementException("could not bid");
        }

    }


    public DutchAuction buyDutchAuction(String auctionId, String token) throws AccessDeniedException {

        try {
            TokenClaim claim = accountsService.getUserClaims(token);
            Buy buy = new Buy(auctionId, claim.getUsername());

            String bidUrl = API_URL + "dutch/buy";
            ResponseEntity<DutchResponse> response = restTemplate.postForEntity(bidUrl, buy, DutchResponse.class);
            int statusCode = response.getStatusCodeValue();
            DutchResponse responseBody = response.getBody();

            return responseBody.getAuction();

        } catch (AccessDeniedException e) {
            throw new AccessDeniedException("user not found");
        } catch (HttpClientErrorException e) {
            throw new NoSuchElementException("could not bid");
        }

    }

    public AuctionType getAuctionType(String auctionId) {
        try {

                String url = String.format(API_URL + "all/type?auctionId=%s", auctionId);
            System.out.println(url);
                ResponseEntity<AuctionType> response = restTemplate.getForEntity(url, AuctionType.class);
                AuctionType responseBody = response.getBody();
                return responseBody;


        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    public List<Auction> getAuctionListWItemId(List<String> itemIds) {
        try {
            List<Auction> auctions = new ArrayList<>();

            List<ForwardAuction> forwardAuctions = this.getAuctionListWItemIdForward(itemIds);

            if (forwardAuctions != null && !forwardAuctions.isEmpty()) {
                auctions.addAll(forwardAuctions);
            }

            List<DutchAuction> dutchAuctions = this.getAuctionListWItemIdDutch(itemIds);

            if (dutchAuctions != null && !dutchAuctions.isEmpty()) {
                auctions.addAll(dutchAuctions);
            }

            return auctions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    private List<ForwardAuction> getAuctionListWItemIdForward(List<String> itemIds) {


        try {

            String forwardUrl = String.format(API_URL + "forward/search/item");


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

// create the HTTP entity
            HttpEntity<Object> requestEntity = new HttpEntity<>(itemIds, headers);

// make the POST request and receive the response
            ParameterizedTypeReference<List<ForwardAuction>> responseType = new ParameterizedTypeReference<List<ForwardAuction>>() {
            };
            ResponseEntity<List<ForwardAuction>> response = restTemplate.exchange(forwardUrl, HttpMethod.POST, requestEntity, responseType);


            List<ForwardAuction> forwardAuctions = response.getBody();


            return forwardAuctions;


        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    private List<DutchAuction> getAuctionListWItemIdDutch(List<String> itemIds) {

        try {

            String forwardUrl = String.format(API_URL + "dutch/search/item");


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

// create the HTTP entity
            HttpEntity<Object> requestEntity = new HttpEntity<>(itemIds, headers);

// make the POST request and receive the response
            ParameterizedTypeReference<List<DutchAuction>> responseType = new ParameterizedTypeReference<List<DutchAuction>>() {
            };
            ResponseEntity<List<DutchAuction>> response = restTemplate.exchange(forwardUrl, HttpMethod.POST, requestEntity, responseType);


            List<DutchAuction> dutchAuctions = response.getBody();

            return dutchAuctions;


        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }


}
