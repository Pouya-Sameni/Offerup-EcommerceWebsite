package com.offerup.controller.controllers;


import com.offerup.controller.Services.AuctionService;
import com.offerup.controller.dtos.auctionDTOs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auctions")
public class AuctionServiceEPs {

    private AuctionService auctionService;

    @Autowired
    public AuctionServiceEPs (AuctionService auctionService){
        this.auctionService = auctionService;
    }


    @PostMapping("/forward/bid")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ForwardAuction> forwardBid(@RequestBody Bid bid, @RequestParam("sessionToken") String token) {

        try {
            ForwardAuction forwardAuction = auctionService.bidOnForwardAuction(bid, token);

            return new ResponseEntity<>(forwardAuction, HttpStatus.OK);

        } catch (AccessDeniedException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dutch/buy")
    @CrossOrigin(origins = "*")
    public ResponseEntity<DutchAuction> dutchBuy(@RequestParam String auctionId, @RequestParam("sessionToken") String token) {

        try {
            DutchAuction dutchAuction = auctionService.buyDutchAuction(auctionId, token);

            return new ResponseEntity<>(dutchAuction, HttpStatus.OK);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }







}
