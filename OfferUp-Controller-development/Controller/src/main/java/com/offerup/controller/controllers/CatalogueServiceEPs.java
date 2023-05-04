package com.offerup.controller.controllers;


import com.offerup.controller.Services.CatalogueService;
import com.offerup.controller.Services.ControllerServices;
import com.offerup.controller.dtos.auctionDTOs.Auction;
import com.offerup.controller.dtos.auctionDTOs.AuctionType;
import com.offerup.controller.dtos.catalogueDTOs.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/catalogue")
public class CatalogueServiceEPs {

    CatalogueService catalogueService;
    ControllerServices controllerServices;

    @Autowired
    public CatalogueServiceEPs (CatalogueService catalogueService, ControllerServices controllerServices)
    {
        this.controllerServices = controllerServices;
        this.catalogueService = catalogueService;
    }

    @GetMapping("/search/description")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Auction>> searchForAuctions(@RequestParam(value = "description") String description,
                                                           @RequestParam("sessionToken") String token) {
        try{
            List<Auction> auctions= this.controllerServices.makeAuctionSearch(description);
            if (auctions == null || auctions.isEmpty())
            {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(auctions, HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/search/id")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Item> searchForItem(@RequestParam(value = "itemId") String itemId,
                                                           @RequestParam("sessionToken") String token) {
        try{
            Item item= this.catalogueService.getItemDetails(itemId);
            if (item == null)
            {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(item, HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }



    @GetMapping("/health")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Item>> health() throws AccessDeniedException {

        return new ResponseEntity<>(catalogueService.getAllMatchingItems("wireless"), HttpStatus.OK);
    }

}