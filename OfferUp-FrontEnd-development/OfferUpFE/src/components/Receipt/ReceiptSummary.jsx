import React, { useEffect } from "react";
import {
  TextField,
  Grid,
  Typography,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@material-ui/core";
import { getProductDetails } from "../../services/Catalogue";
import { useState } from "react";

const ReceiptSummary = ({ receipt, shipping }) => {
  const [productDetails, setProductDetails] = useState({});
  const [shippingInfo, setShippingInfo] = useState({});
  const fetchData = async () => {
    const bodyReturned = await getProductDetails(receipt.itemId);
    console.log(bodyReturned);
    if (shipping == "expedited") {
      setShippingInfo(bodyReturned.shippingOptions[1]);
    } else {
      setShippingInfo(bodyReturned.shippingOptions[0]);
    }
    setProductDetails(bodyReturned);
    console.log(bodyReturned);
  };

  useEffect(() => {
    fetchData();
  }, [receipt, shipping]);

  
  console.log(receipt);
  return (
    <Grid container spacing={3} alignItems="center">
      <Grid item xs={12}>
        {/* <Typography
          variant="h6"
          style={{
            fontFamily: "poppins",
            fontSize: 25,
            fontWeight: 500,
            textAlign: "center",
            marginTop: 30,
          }}
        >
          Thank You For Your Purchase!
        </Typography> */}
        <div
          style={{
            fontFamily: "poppins",
            fontSize: 18,
            fontWeight: 600,
            textAlign: "left",
          }}
        >
          Receipt
        </div>
      </Grid>
      <Grid item xs={12} sm={12} style={{ alignItems: "flex-start" }}>
        
        <TextField
        label="Item Name"
          name="itemDetails"
          id="filled-read-only-input"
          fullWidth
          defaultValue=""
          InputProps={{
            readOnly: true,
          }}
          variant="filled"
          value={`${productDetails.itemName}`}
        />
      </Grid>
     
      <Grid item xs={12} sm={6} style={{ alignItems: "flex-start" }}>
        <TextField
          name="itemDetails"
          label="Item Details"
          id="filled-read-only-input"
          fullWidth
          multiline
          defaultValue=""
          InputProps={{
            readOnly: true,
          }}
          variant="filled"
          value={`${productDetails.description}`}
        />
      </Grid>
      <Grid item xs={1} sm={6} style={{ alignItems: "flex-start" }}>
       
        <TextField
        label="Shipping Time"
          name="address"
          id="filled-read-only-input"
          fullWidth
          multiline
          defaultValue=""
          InputProps={{
            readOnly: true,
          }}
          variant="filled"
          value={`Item Will Ship in ${shippingInfo.minDays} to ${shippingInfo.maxDays} days`}
        />
      </Grid>
      <Grid item xs={6} sm={6}>
        <TextField
          label="Shipping Method"
          name="shippingOptions"
          id="filled-read-only-input"
          fullWidth
          maxWidth="300px"
          defaultValue=""
          InputProps={{
            readOnly: true,
          }}
          variant="filled"
          value={`${shippingInfo.type}`}
        />
      </Grid>
      <Grid item xs={2} sm={2} style={{ alignItems: "flex-start" }}>
        
        <TextField
        label="Total Price"
          name="orderTotal"
          id="filled-read-only-input"
          fullWidth
          multiline
          defaultValue=""
          InputProps={{
            readOnly: true,
          }}
          variant="filled"
          value={`${receipt.totalPrice}`}
        />
      </Grid>
    </Grid>
  );
};

export default ReceiptSummary;
