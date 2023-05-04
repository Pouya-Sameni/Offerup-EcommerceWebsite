import * as React from "react";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import { useState } from "react";
import { useEffect } from "react";
import moment from "moment";

export const AuctionDetails = (auctionInfo) => {
  // console.log(auctionInfo.auctionInfo);

  const [timeLeft, setTimeLeft] = useState(getTimeLeft());

  useEffect(() => {
    const timer = setInterval(() => {
      setTimeLeft(getTimeLeft());
    }, 1000);

    return () => clearInterval(timer);
  }, [auctionInfo]);

  function getTimeLeft() {
    const now = new Date().getTime();
    const endTime = moment(
      auctionInfo.auctionInfo.endTimeOfAuction,
      "YYYY-MM-DD-HH:mm"
    ).valueOf();
    // console.log(endTime);

    const timeLeft = endTime - now;

    if (timeLeft < 0) {
      return "Auction has ended!";
    }

    const days = Math.floor(timeLeft / (1000 * 60 * 60 * 24));
    const hours = Math.floor(
      (timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
    );
    const minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);

    return (
      <div>
        Time Left:{" "}
        <span style={{ color: "red" }}>
          {days}d {hours}h {minutes}m {seconds}s
        </span>
      </div>
    );
  }

  return (
    <Box
      component="form"
      sx={{
        "& .MuiTextField-root": { m: 1, width: "20ch" },
        backgroundColor: "#f0edea",
        fontFamily: "poppins ",
        fontSize: "28px",
        height: "450px",
        width: "700px",
        padding: "40px",
        marginTop: 10,
        marginLeft: 15,
        borderRadius: 10,
      }}
      noValidate
      autoComplete="off"
    >
      <div>
        <h1 className="flex-1 ml-5 font-poppins font-semibold text-[54px] text-babyblue">
          Auction Details
        </h1>
      </div>
      <div>
        <Grid container spacing={4}>
          <Grid item xs={5.6}>
            <TextField
              style={{ width: 300 }}
              id="filled-read-only-input"
              label="Auction ID"
              defaultValue=""
              InputProps={{
                readOnly: true,
              }}
              variant="filled"
              value={auctionInfo.auctionInfo.auctionId}
            />
          </Grid>
          <Grid item xs={6}>
            <TextField
              style={{ width: 300 }}
              id="filled-read-only-input"
              label="Auction Type"
              defaultValue=""
              InputProps={{
                readOnly: true,
              }}
              variant="filled"
              value={auctionInfo.auctionInfo.auctionType}
            />
          </Grid>
        </Grid>
      </div>
      <div>
        <Grid container spacing={4}>
          <Grid item xs={5.6}>
            <TextField
              style={{ width: 300 }}
              id="filled-read-only-input"
              label="Current Price"
              defaultValue=""
              InputProps={{
                readOnly: true,
              }}
              variant="filled"
              value={`$${auctionInfo.auctionInfo.currentPrice}`}
            />
          </Grid>
          <Grid item xs={6}>
            <TextField
              style={{ width: 300 }}
              id="filled-read-only-input"
              label="Current Highest Bidder"
              defaultValue=""
              InputProps={{
                readOnly: true,
              }}
              variant="filled"
              value={auctionInfo.auctionInfo.highestBidderUserId}
            />
          </Grid>
        </Grid>
      </div>
      <div>
        <Grid container spacing={4}>
          <Grid item xs={5.6}>
            <TextField
              style={{ width: 300 }}
              id="filled-read-only-input"
              label="Auction Start Time"
              defaultValue=""
              InputProps={{
                readOnly: true,
              }}
              variant="filled"
              value={auctionInfo.auctionInfo.startTimeOfAuction}
            />
          </Grid>
          <Grid item xs={6}>
            <TextField
              style={{ width: 300 }}
              id="filled-read-only-input"
              label="Auction End Time"
              defaultValue=""
              InputProps={{
                readOnly: true,
              }}
              variant="filled"
              value={auctionInfo.auctionInfo.endTimeOfAuction}
            />
          </Grid>
        </Grid>
        <div
          id="countdown"
          style={{ fontSize: 28, textAlign: "center", marginTop: 20 }}
        >
          {timeLeft}
        </div>
      </div>
    </Box>
  );
};

export const ItemDetails = (itemInfo) => {
  return (
    <Box
      component="form"
      sx={{
        "& .MuiTextField-root": { m: 1, width: "20ch" },
        backgroundColor: "#f0edea",
        fontFamily: "poppins ",
        fontSize: "28px",
        height: "450px",
        width: "700px",
        padding: "40px",
        marginTop: 10,
        marginLeft: 5,
        borderRadius: 10,
      }}
      noValidate
      autoComplete="off"
    >
      <div>
        <h1 className="flex-1 ml-5 font-poppins font-semibold text-[54px] text-babyblue">
          Item Details
        </h1>
      </div>
      <div>
        <Grid container spacing={0}>
          <Grid item xs={5}>
            <TextField
              style={{ width: 250 }}
              id="filled-read-only-input"
              label="Item ID"
              defaultValue=""
              InputProps={{
                readOnly: true,
              }}
              value={itemInfo.itemInfo.id}
              variant="filled"
            />
          </Grid>
          <Grid item xs={6}>
            <TextField
              style={{ width: 350 }}
              id="filled-read-only-input"
              label="Item Name"
              defaultValue=""
              InputProps={{
                readOnly: true,
              }}
              variant="filled"
              value={itemInfo.itemInfo.itemName}
            />
          </Grid>
        </Grid>
      </div>
      <div>
        <Grid container spacing={4}>
          <Grid item xs={6}>
            <TextField
              style={{ width: 610 }}
              multiline
              rows={6}
              id="filled-read-only-input"
              label="Item Description"
              defaultValue=""
              InputProps={{
                readOnly: true,
              }}
              variant="filled"
              value={itemInfo.itemInfo.description}
            />
          </Grid>
        </Grid>
      </div>
    </Box>
  );
};
