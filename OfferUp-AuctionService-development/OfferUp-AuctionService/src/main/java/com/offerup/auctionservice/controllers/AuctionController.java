package com.offerup.auctionservice.controllers;


import com.offerup.auctionservice.dtos.AuctionType;
import com.offerup.auctionservice.dtos.DutchAuction;
import com.offerup.auctionservice.dtos.ForwardAuction;
import com.offerup.auctionservice.dtos.ForwardResponse;
import com.offerup.auctionservice.dutchAuctionServices.QueryDutchService;
import com.offerup.auctionservice.dutchAuctionServices.UpdateDutchService;
import com.offerup.auctionservice.forwardAuctionServices.QueryForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auction/all")
public class AuctionController {

    QueryForwardService queryForwardService;
    QueryDutchService queryDutchService;

    @Autowired
    public AuctionController (QueryDutchService queryDutchService, QueryForwardService queryForwardService){
        this.queryDutchService = queryDutchService;
        this.queryForwardService = queryForwardService;
    }


    @GetMapping("/type")
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuctionType> auctionDetails(@RequestParam(required = true) String auctionId) {
        try {


            DutchAuction dutchAuction = queryDutchService.auctionDetails(auctionId);
            return new ResponseEntity<>(AuctionType.DUTCH, HttpStatus.OK);

        }
        catch (Exception e){

        }

        try {

            ForwardAuction forwardAuction = queryForwardService.auctionDetails(auctionId);



                return new ResponseEntity<>(AuctionType.FORWARD, HttpStatus.OK);


        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
