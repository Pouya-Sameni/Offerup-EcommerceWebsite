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
@Document(value="payments")
public class Receipt {
    @Id
    private ObjectId purchaseId;
    private User soldTo;
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
