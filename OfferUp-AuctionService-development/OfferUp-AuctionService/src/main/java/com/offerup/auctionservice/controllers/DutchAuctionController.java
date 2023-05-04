package com.offerup.auctionservice.controllers;


import com.offerup.auctionservice.dtos.*;
import com.offerup.auctionservice.dutchAuctionServices.QueryDutchService;
import com.offerup.auctionservice.dutchAuctionServices.UpdateDutchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auction/dutch")

public class DutchAuctionController {

    QueryDutchService queryDutchService;
    UpdateDutchService updateDutchService;

    @Autowired
    public DutchAuctionController (QueryDutchService queryDutchService, UpdateDutchService updateDutchService){
        this.queryDutchService = queryDutchService;
        this.updateDutchService = updateDutchService;
    }

    @GetMapping("/search/id")
    @CrossOrigin(origins = "*")
    public ResponseEntity<DutchResponse> auctionDetails(@RequestParam(required = true) String auctionId) {
        try {
            DutchAuction auction = queryDutchService.auctionDetails(auctionId);
            return new ResponseEntity<>(new DutchResponse(auction), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new DutchResponse("Auction object not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/search/item")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<DutchAuction>> searchByItem(@RequestBody List<String> itemList) {

        try{

            List<DutchAuction> auctions = queryDutchService.searchByItems(itemList);
            return new ResponseEntity<>(auctions, HttpStatus.OK);

        }catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/user/winner")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Boolean> isUserWinner(@RequestParam(value = "auctionId") String auctionId,
                                                @RequestParam(value = "userId") String userId) {
        try{
            return new ResponseEntity<>(queryDutchService.isUserWinner(auctionId, userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/buy")
    @CrossOrigin(origins = "*")
    public ResponseEntity<DutchResponse> buyAuction(@RequestBody Buy buy) {
       try{
           DutchAuction auction = updateDutchService.buyAuction(buy.getAuctionId(), buy.getUserId());
           return new ResponseEntity<>(new DutchResponse(auction), HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new DutchResponse("Auction object not found"), HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/reset")
    @CrossOrigin(origins = "*")
    public void reset() {
        updateDutchService.reset();
    }




    @GetMapping("/getall")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<DutchAuction>> testDB() {
        return new ResponseEntity<>(queryDutchService.getAllAuctions(), HttpStatus.OK);
    }
}
