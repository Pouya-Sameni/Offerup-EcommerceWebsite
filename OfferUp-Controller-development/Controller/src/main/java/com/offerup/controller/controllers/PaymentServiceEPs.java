package com.offerup.controller.controllers;


import com.offerup.controller.Services.CatalogueService;
import com.offerup.controller.Services.ControllerServices;
import com.offerup.controller.dtos.auctionDTOs.Auction;
import com.offerup.controller.dtos.auctionDTOs.AuctionType;
import com.offerup.controller.dtos.catalogueDTOs.Item;
import com.offerup.controller.dtos.paymentDTOs.Payment;
import com.offerup.controller.dtos.paymentDTOs.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/payment")
public class PaymentServiceEPs {

    ControllerServices controllerServices;

    @Autowired
    public PaymentServiceEPs ( ControllerServices controllerServices)
    {
        this.controllerServices = controllerServices;
    }


    //TODO: Must Be Implemented
    @GetMapping("/page/{auctionId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Object>> getPaymentPage(@PathVariable("auctionId") String auctionId ,
                                                              @RequestParam("sessionToken") String token) {
        try{
            Map <String, Object> response = controllerServices.getPaymentPreview(auctionId, token);
            if (response == null || response.isEmpty())
            {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pay/{auctionId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Receipt> makePayment(@PathVariable("auctionId") String auctionId ,
                                               @RequestParam("sessionToken") String token, @RequestBody Payment payment) {
        try{
            Receipt receipt = this.controllerServices.payForAuction(auctionId, token, payment);

            if (receipt == null)
            {
                throw new Exception("Error with Payment");
            }
            return new ResponseEntity<>(receipt, HttpStatus.OK);

        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/health")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> health() throws AccessDeniedException {

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
