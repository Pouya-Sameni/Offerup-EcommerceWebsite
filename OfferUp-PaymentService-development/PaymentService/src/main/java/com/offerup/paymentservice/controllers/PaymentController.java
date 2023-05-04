package com.offerup.paymentservice.controllers;

import com.offerup.paymentservice.datatransferobjects.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offerup.paymentservice.datatransferobjects.Payment;
import com.offerup.paymentservice.services.PaymentService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService){
		this.paymentService = paymentService;
	}
	
	@PostMapping("/pay")
	public ResponseEntity<Receipt> Pay(@RequestBody Map<String, Object> payment) {
		System.out.println(payment);
		try {
			Receipt response =  paymentService.pay(payment);
			if (response == null)
			{
				return ResponseEntity.badRequest().build();
			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
}
