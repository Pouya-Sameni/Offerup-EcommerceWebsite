package com.offerup.controller.dtos.paymentDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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