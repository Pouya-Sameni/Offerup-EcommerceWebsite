import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Stepper, Step, StepLabel, Button, Box, Grid } from "@material-ui/core";
import PaymentStepIcons from "../Payment/PaymentStepIcons";
import StepConnector from "../Payment/StepConnector";

import ContactForm from "../Contact/ContactForm";
import PaymentForm from "./PaymentForm";
import ShippingForm from "../Shipping/ShippingForm";
import ReceiptSummary from "../Receipt/ReceiptSummary";
import { payForAuction } from "../../services/Payment";
import { useEffect } from "react";
const style = makeStyles((theme) => ({

  button: {
    marginRight: theme.spacing(1),
  },
  mainBox: {
    position: "relative",
    marginTop: "-8px",
    padding: "10px 20px",
    borderBottomRightRadius: "4px",
    borderBottomLeftRadius: "4px",
    background: theme.palette.background.default,
  },
  stepper: {
    height: "calc(10vh - 40px)",
    minHeight: "55px",
  },
  form: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-around",
    width: "100%",
  },
}));

const StepContent = ({ step, result, cardInfo, setCardInfo, setTotalPrice, receipt }) => {
  const [shipping, setShipping] = useState(null);

  const handleCardInfoChange = (cardInfo) => {
    setCardInfo(cardInfo);
  };

  const handlePriceChange = (price) => {
    setTotalPrice(price);
  };

  const handleShippingChoice = (shippingChoice) => {
    setShipping(shippingChoice);
  };
  
 
 
  switch (step) {
    case 0:
      return <ContactForm userInfo={result.result.userInfo} />;
    case 1:
      return <PaymentForm auctionInfo={result.result.auctionInfo} handleCardInfoChange={handleCardInfoChange} cardInfo={cardInfo} setCardInfo={setCardInfo} />;
    case 2:
      return (
        <ShippingForm
          itemInfo={result.result.itemInfo}
          auctionInfo={result.result.auctionInfo}
          handlePriceChange={handlePriceChange}
          handleShippingChoice={handleShippingChoice}
        />
      );
    case 3:
      return (
        <ReceiptSummary
            receipt={receipt}
            shipping = {shipping}
        />
      );
    default:
      return <></>;
  }
};

const PaymentStepper = (result) => {
  const [totalPrice, setTotalPrice] = useState(0);
  const [activeStep, setActiveStep] = useState(0);
  const [receipt, setReceipt] = useState(null);
  const [cardInfo, setCardInfo] = useState({
    "cardNumber": '',
    "cardCVV": '',
    "cardExpirationDate": '',
    "totalPrice": 0
  });
  const classes = style();
  const handleNext = async () =>{
    if (activeStep >= 2 ){
      cardInfo.totalPrice = totalPrice;
      const FullPaymentObject = {
          "userInfo" : result.userInfo,
          "paymentInfo" : cardInfo,
          "auctionId" : result.result.auctionInfo.auctionId,
          "itemId" : result.result.auctionInfo.itemId
      };

      const bodyReturned = await payForAuction(FullPaymentObject);
      setReceipt(bodyReturned);
      console.log(bodyReturned);

    }
    setActiveStep((prevActiveStep) => prevActiveStep + 1);

  };


  const handleBack = () =>{
    setActiveStep((prevActiveStep) => prevActiveStep - 1);};
  // const handleReset = () => setActiveStep(0);

  return (
    <>
      <Stepper
        alternativeLabel
        className={classes.stepper}
        connector={<StepConnector />}
        activeStep={activeStep}
      >
        {/* Change the number of loops here based on StepContent */}
        {[1, 2, 3, 4].map((e) => (
          <Step key={e}>
            <StepLabel StepIconComponent={PaymentStepIcons} />
          </Step>
        ))}
      </Stepper>
      <Box className={classes.mainBox}>
        <Grid
          container
          spacing={3}
          direction="column"
          justifyContent="space-around"
          alignItems="center"
          style={{ height: "400px" }}
        >
          <StepContent step={activeStep} result={result} cardInfo = {cardInfo} setCardInfo = {setCardInfo} setTotalPrice = {setTotalPrice} receipt = {receipt}/>
          {activeStep === 4 ? (
            <div className={classes.button}>Thank you for shopping with OfferUp!</div>
          ) : (
            <form
              className={classes.form}
              onSubmit={(e) => {
                e.preventDefault();
                handleNext();
              }}
            >
              <Grid container spacing={3}>
                {/* <StepContent step={activeStep} /> */}
                <Grid container item justifyContent="flex-end">
                  <Button
                    disabled={activeStep === 0}
                    className={classes.button}
                    onClick={handleBack}
                  >
                    Back
                  </Button>
                  <Button
                    variant="contained"
                    color="primary"
                    className={classes.button}
                    type="submit"
                  >
                    {activeStep === 3 ? "Confirm" : "Next"}
                  </Button>
                </Grid>
              </Grid>
            </form>
          )}
        </Grid>
      </Box>
    </>
  );
};

export default PaymentStepper;
