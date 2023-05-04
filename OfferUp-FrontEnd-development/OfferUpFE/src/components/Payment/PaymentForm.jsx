import React, { useState } from "react";
import { TextField, Grid, Typography, InputLabel } from "@material-ui/core";
import InputMask from "react-input-mask";

const PaymentForm = ({ auctionInfo, handleCardInfoChange }) => {
  const [cardNumber, setCardNumber] = useState("");
  const [expirationDate, setExpirationDate] = useState("");
  const [cvc, setCvc] = useState("");

  const updateFields = () => {
    const data = {
      cardNumber: cardNumber,
      cardCVV: cvc,
      cardExpirationDate: expirationDate,
      totalPrice: auctionInfo.currentPrice,
    };

    handleCardInfoChange(data);
  };

  const handleCardNumberChange = (event) => {
    setCardNumber(event.target.value);
    updateFields();
  };

  const handleExpirationDateChange = (event) => {
    setExpirationDate(event.target.value);
    updateFields();
  };

  const handleCvcChange = (event) => {
    setCvc(event.target.value);
    updateFields();
  };

  return (
    <Grid container spacing={3} alignItems="center">
      <Grid item xs={12}>
        <Typography variant="h6">Payment Details</Typography>
      </Grid>
      <Grid item xs={12} sm={6}>
        <InputLabel htmlFor="amount" position="top">
          Amount (*Note: shipping fee will be applied during next step)
        </InputLabel>
        <TextField
          name="amount"
          variant="filled"
          multiline
          required
          fullWidth
          maxWidth="300px"
          value={auctionInfo.currentPrice}
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          label="Credit Card Number"
          name="ccnumber"
          variant="outlined"
          required
          fullWidth
          InputLabelProps={{ shrink: true }}
          maxWidth="300px"
          value={cardNumber}
          onChange={handleCardNumberChange}
          InputProps={{
            inputComponent: InputMask,
            inputProps: {
              mask: "9999 9999 9999 9999",
              maskPlaceholder: "",
            },
          }}
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          label="Expiration Date"
          name="ccexp"
          variant="outlined"
          required
          fullWidth
          InputLabelProps={{ shrink: true }}
          maxWidth="300px"
          value={expirationDate}
          onChange={handleExpirationDateChange}
          InputProps={{
            inputComponent: InputMask,
            inputProps: {
              mask: "99/99",
              maskPlaceholder: "",
            },
          }}
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          label="CVC"
          name="cvc"
          variant="outlined"
          required
          fullWidth
          InputLabelProps={{ shrink: true }}
          maxWidth="300px"
          value={cvc}
          onChange={handleCvcChange}
          InputProps={{
            inputComponent: InputMask,
            inputProps: {
              mask: "999",
              maskPlaceholder: "",
            },
          }}
        />
      </Grid>
    </Grid>
  );
};

export default PaymentForm;
