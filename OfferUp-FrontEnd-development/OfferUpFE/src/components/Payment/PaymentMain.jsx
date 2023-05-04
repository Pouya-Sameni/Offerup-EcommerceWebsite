import React from "react";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Container from "@mui/material/Container";
import PaymentStepper from "./PaymentStepper";

export default function PaymentMain({result}) {

  // console.log(result)

  return (
    <Box
      sx={{
        backgroundColor: "white",
        color: "black",
        fontFamily: "poppins ",
        fontSize: "28px",
        height: "800px",
        width: "1050px",
        padding: "40px",
        marginTop: 5,
        marginLeft: 30,
      }}
    >
      <div>
        <h1 className="flex-1 ml-5 font-poppins font-semibold text-[54px] text-babyorange">
          Payment Details
        </h1>
      </div>

      <Container
        sx={{
          position: "relative",
          zIndex: "1100",
          marginTop: "15px",
          marginBottom: "50px",
        }}
      >
        <Paper elevation={10}>
          <PaymentStepper result={result} />
        </Paper>
      </Container>
    </Box>
  );
}
