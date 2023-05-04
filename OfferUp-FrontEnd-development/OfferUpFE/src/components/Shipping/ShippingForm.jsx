import React from "react";
import {
  TextField,
  Grid,
  Typography,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@material-ui/core";
import { useState} from "react";
import { useEffect } from "react";

const ShippingForm = ({ itemInfo, auctionInfo, handlePriceChange, handleShippingChoice }) => {
  const [shipping, setShipping] = useState("");
  const [estimatedTime, setEstimatedTime] = useState("");
  const [shippingPrice, setShippingPrice] = useState("");
  const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    const selectedShippingOption = itemInfo.shippingOptions.find(
      (option) => option.type === shipping.toUpperCase()
    );

    if (selectedShippingOption) {
      const { minDays, maxDays, fee } = selectedShippingOption;
      setEstimatedTime(`${minDays} - ${maxDays} days`);
      setShippingPrice(fee);
    } else {
      setEstimatedTime("");
      setShippingPrice("");
    }
  }, [itemInfo, shipping]);

  useEffect(() => {
    const currentPrice = auctionInfo.currentPrice || 0;
    const shippingCost = shippingPrice || 0;
    setTotalPrice(currentPrice + shippingCost);
    handlePriceChange((currentPrice + shippingCost));
  }, [auctionInfo, shippingPrice]);

  const handleChange = (event) => {
    if (event.target.value) {
      setShipping(event.target.value.toLowerCase());
      handleShippingChoice(event.target.value.toLowerCase());
    } else {
      setShipping("");
    }
  };

  return (
    <Grid container spacing={3} alignItems="center">
      <Grid item xs={12}>
        <Typography variant="h6">Shipping Details</Typography>
      </Grid>
      <Grid item xs={12} sm={6}>
        <FormControl fullWidth variant="outlined">
          <InputLabel>Shipping Type</InputLabel>
          <Select
            labelId="Shipping"
            id="Select Shipping"
            label="Select Shipping Type"
            value={shipping}
            onChange={handleChange}
          >
            <MenuItem value="regular">Regular</MenuItem>
            <MenuItem value="expedited">Expedited</MenuItem>
          </Select>
        </FormControl>
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          label="Estimated Time of Arrival"
          name="shippingPrice"
          id="filled-read-only-input"
          fullWidth
          maxWidth="300px"
          defaultValue=""
          InputProps={{
            readOnly: true,
          }}
          variant="filled"
          value={estimatedTime}
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          label="Shipping Price"
          name="newPrice"
          id="filled-read-only-input"
          fullWidth
          maxWidth="300px"
          defaultValue=""
          InputProps={{
            readOnly: true,
          }}
          variant="filled"
          value={shippingPrice}
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          label="Total Price"
          name="totalPrice"
          id="filled-read-only-input"
          fullWidth
          maxWidth="300px"
          defaultValue=""
          InputProps={{
            readOnly: true,
          }}
          variant="filled"
          value={totalPrice}
        />
      </Grid>
    </Grid>
  );
};

export default ShippingForm;
