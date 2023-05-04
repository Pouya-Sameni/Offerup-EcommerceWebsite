package com.offerup.controller.dtos.paymentDTOs;

import com.offerup.controller.dtos.accountsDTOs.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    @Id
    private ObjectId purchaseId;
    private UserDto soldTo;
    private String timeOfPurchase;
    private String cardNumber;
    private String cardCVV;
    private String cardExpirationDate;
    private float totalPrice;
    private String auctionId;
    private String itemId;

    public void setCreditInfo (Payment payment){
        this.setCardNumber(payment.getCardNumber());
        this.setCardCVV(payment.getCardCVV());
        this.setCardExpirationDate(payment.getCardExpirationDate());
        this.setTotalPrice(payment.getTotalPrice());
    }

}
