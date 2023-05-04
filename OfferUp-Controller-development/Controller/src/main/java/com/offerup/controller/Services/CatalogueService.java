package com.offerup.controller.Services;


import com.offerup.controller.dtos.auctionDTOs.ForwardResponse;
import com.offerup.controller.dtos.catalogueDTOs.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CatalogueService {

    @Value("${catalogueService}")
    private String API_URL;


    private RestTemplate restTemplate;
    private AccountsService accountsService;

    @Autowired
    public CatalogueService(AccountsService accountsService) {
        this.restTemplate = new RestTemplate();
        this.accountsService = accountsService;
    }

    public List<Item> getAllMatchingItems(String itemDescription) {


            String url = String.format((API_URL + "search/description?description=%s"), itemDescription);

            ResponseEntity<List<Item>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Item>>() {
                    });
            List<Item> items = response.getBody();
            return items;
    }

    public Item getItemDetails(String itemId) {

        try{
            String url = String.format((API_URL + "search/id?itemId=%s"), itemId);
            System.out.println(url);
            ResponseEntity<Item> response = restTemplate.getForEntity(url, Item.class);
            Item item = response.getBody();

            return item;

        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }
    public List<String> getAllMatchingItemsId(String itemDescription) {
        try {
            List<Item> items = getAllMatchingItems(itemDescription);

            Set<String> ids = new HashSet<>();
            for (Item item : items) {
                ids.add(item.getId().toString());
            }
            return new ArrayList<>(ids);

        } catch (Exception e) {
            return null;
        }

    }
}
