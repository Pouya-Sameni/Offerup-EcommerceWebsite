package com.offerup.paymentservice.repositories;

import com.offerup.paymentservice.datatransferobjects.Receipt;
import org.springframework.stereotype.Repository;

import com.offerup.paymentservice.datatransferobjects.Payment;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface PaymentDB extends MongoRepository<Receipt, String>{
	Receipt insert(Receipt receipt);

	Receipt findByAuctionId(String id);
}
