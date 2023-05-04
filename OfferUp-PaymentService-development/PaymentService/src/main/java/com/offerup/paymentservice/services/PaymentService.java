package com.offerup.paymentservice.services;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offerup.paymentservice.datatransferobjects.Receipt;
import com.offerup.paymentservice.datatransferobjects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offerup.paymentservice.datatransferobjects.Payment;
import com.offerup.paymentservice.repositories.PaymentDB;

@Service
public class PaymentService {

	private PaymentDB paymentDB;
	
	@Autowired
	public PaymentService(PaymentDB paymentDB) {
		this.paymentDB = paymentDB;
	}


	public boolean paymentExists(String auctionId) {
		if (paymentDB.findByAuctionId(auctionId) == null)
		{
			return false;
		}
		return true;
	}
	public Receipt pay(Map<String, Object> paymentBody) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		User user = objectMapper.convertValue(paymentBody.get("userInfo"), User.class);
		Payment payment = objectMapper.convertValue(paymentBody.get("paymentInfo"), Payment.class);
		String auctionId = objectMapper.convertValue(paymentBody.get("auctionId"), String.class);
		String itemId = objectMapper.convertValue(paymentBody.get("itemId"), String.class);

		if (paymentExists(auctionId)) {
			return null;
		}

		Receipt receipt = new Receipt();
		receipt.setAuctionId(auctionId);
		receipt.setSoldTo(user);
		receipt.setItemId(itemId);
		receipt.setTimeOfPurchase(getCurrentTimeFormatted());
		receipt.setCreditInfo(payment);

		return paymentDB.insert(receipt);
	}

	public  static String getCurrentTimeFormatted() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		String formattedTime = now.format(formatter);
		return formattedTime;
	}
}