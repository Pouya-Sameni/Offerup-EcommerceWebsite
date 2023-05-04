import * as React from "react";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import { AuctionDetails, ItemDetails } from "../Auction/AuctionComponents";
import Button from "@mui/material/Button";
import { useState } from "react";
import { forwardBid } from "../../services/Auctions";
import { getPaymentPage } from "../../services/Payment";
import { useNavigate } from "react-router-dom";

const ForwardComponent = (info) => {
  const navigate = useNavigate();
  const [bidAmount, setBid] = useState(null);

  const handleBidAmountChange = (event) => {
    setBid(event.target.value);
  };
  

  const handleBidSubmit = async (event) => {
    // console.log(bidAmount);
    // console.log("here");

    if (bidAmount) {
      try {
        setBid(parseFloat(bidAmount));
        console.log(bidAmount);
        if (bidAmount <= 1000000){
          const result = await forwardBid(bidAmount, info.auctionInfo.auctionId);
        }else{
          window.alert('Bid Amount Cannot Surpass $1,000,000');
          
        }
        
      } catch (e) {}
    }
  };

  const handlePayNow = async (event) => {
    try {
      const result = await getPaymentPage(info.auctionInfo.auctionId);
      console.log(result);

      if (result.userInfo.username === info.auctionInfo.highestBidderUserId) {
        navigate("/payment", {
          state: {
            result
          },
        });
      } else {
        navigate("/home");
      }
    } catch (e) {}
  };

  return (
    <div>
      <Grid container spacing={2}>
        <Grid item xs={6} sm={6}>
          <AuctionDetails auctionInfo={info.auctionInfo} />
        </Grid>
        <Grid item xs={6} sm={6}>
          <ItemDetails itemInfo={info.itemInfo} />
        </Grid>
      </Grid>
      <div>
        <Box sx={{ height: "100", marginTop: 4, marginLeft: 15 }}>
          <Box sx={{ marginBottom: 2 }}>
            {!info.auctionInfo.auctionEnded && (
              <TextField
                required
                id="outlined-required"
                label="Bid Amount"
                defaultValue=""
                style={{ backgroundColor: "white" }}
                value={bidAmount}
                onChange={handleBidAmountChange}
              />
            )}
          </Box>
          <Box>
            {!info.auctionInfo.auctionEnded && (
              <Button
                type="submit"
                variant="contained"
                sx={{
                  mb: 100,
                  fontSize: "24px",
                  fontFamily: "poppins",
                  fontWeight: "bold",
                  padding: "20px 40px",
                  height: "60px",
                  backgroundColor: "pink",
                  color: "#ffffff",
                  width: "30%",
                }}
                onClick={handleBidSubmit}
              >
                Place Bid Now!
              </Button>
            )}

            {info.auctionInfo.auctionEnded && (
              <Button
                type="submit"
                variant="contained"
                sx={{
                  mb: 100,
                  fontSize: "24px",
                  fontFamily: "poppins",
                  fontWeight: "bold",
                  padding: "20px 40px",
                  height: "60px",
                  backgroundColor: "pink",
                  color: "#ffffff",
                  width: "30%",
                }}
                onClick={handlePayNow}
              >
                Pay Now!
              </Button>
            )}
          </Box>
        </Box>
      </div>
    </div>
  );
};

export default ForwardComponent;
