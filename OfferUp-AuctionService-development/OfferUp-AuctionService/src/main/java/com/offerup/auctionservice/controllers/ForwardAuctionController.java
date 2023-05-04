package com.offerup.auctionservice.controllers;


import com.offerup.auctionservice.dtos.*;
import com.offerup.auctionservice.forwardAuctionServices.QueryForwardService;
import com.offerup.auctionservice.forwardAuctionServices.UpdateForwardService;
import com.offerup.auctionservice.websockethandlers.ForwardWSHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auction/forward")
public class ForwardAuctionController {

    QueryForwardService queryForwardService;
    UpdateForwardService updateForwardService;
    ForwardWSHandler forwardWSHandler;


    @Autowired
    public ForwardAuctionController (QueryForwardService queryForwardService, UpdateForwardService updateForwardService,
                                     ForwardWSHandler forwardWSHandler){
     this.queryForwardService = queryForwardService;
     this.updateForwardService = updateForwardService;
     this.forwardWSHandler = forwardWSHandler;
    }

    @GetMapping("/search/id")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ForwardResponse> auctionDetails(@RequestParam(required = true) String auctionId) {
        try {
            ForwardAuction auction = queryForwardService.auctionDetails(auctionId);
            return new ResponseEntity<>(new ForwardResponse(auction), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ForwardResponse("Auction object not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bid")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ForwardResponse> updateBid(@RequestBody Bid bid) {

        try{
            System.out.println(bid.getUserId());
            ForwardAuction auction = updateForwardService.updateAuctionBid(bid);
            return new ResponseEntity<>(new ForwardResponse(auction), HttpStatus.OK);


        }catch (Exception e)
        {
            return new ResponseEntity<>(new ForwardResponse("Auction bid not changed"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/search/item")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ForwardAuction>> searchByItem(@RequestBody List<String> itemList) {

        try{

            List<ForwardAuction> auctions = queryForwardService.searchByItems(itemList);
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
            return new ResponseEntity<>(queryForwardService.isUserWinner(auctionId, userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/reset")
    @CrossOrigin(origins = "*")
    public void reset(@RequestParam int time) {
        updateForwardService.reset(time);
    }




}
