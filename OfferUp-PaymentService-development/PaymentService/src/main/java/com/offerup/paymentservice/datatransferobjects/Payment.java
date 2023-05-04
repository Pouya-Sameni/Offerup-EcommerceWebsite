package com.offerup.paymentservice.datatransferobjects;

import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	private String cardNumber;
	private String cardCVV;
	private String cardExpirationDate;
	private float totalPrice;


}
