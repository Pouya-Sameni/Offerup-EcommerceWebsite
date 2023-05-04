import React from "react";
import NavBar from "../components/Bar/NavBar";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import Cookies from "js-cookie";

import balloons from "../assets/balloons.jpg";
import shoppingbag from "../assets/shoppingbag.png";
import goodies from "../assets/goodies.png";

const styles = {
  container: "w-screen h-screen grid grid-rows-2 md:grid-cols-2 sm:grid-cols-1",
  leftPanel:
    "w-full h-full bg-black md:h-screen flex justify-center items-center",
  rightPanel: "w-full h-full  bg-black md:h-screen flex justify-end",
};

const AboutPage = () => {

  const sessionToken = Cookies.get("sessionToken");

  if(!sessionToken){
    window.location.href = "/404";
    return null;
  }

  return (
    <div>
      <NavBar />
      <div>
        <img
          src={balloons}
          alt="Balloons"
          style={{
            position: "absolute",
            left: "50%",
            marginTop: "10%",
            maxWidth: "45%",
          }}
        />
      </div>

      <Card
        sx={{
          height: "550px",
          width: "600px",
          marginTop: "10%",
          marginLeft: "10%",
          backgroundColor: "#e6f3fa",
          borderRadius: "20px",
        }}
      >
        <CardContent>
          <Typography
            sx={{
              fontFamily: "poppins",
              fontSize: "65px",
              padding: "5%",
            }}
          >
            OUR <br />
            MISSION
          </Typography>

          <Typography
            sx={{
              fontFamily: "poppins",
              fontSize: "20px",
              padding: "1%",
            }}
          >
            Welcome to OfferUp! The online auction platform that lets you bid
            more and win more. Our mission is to provide a seamless and
            user-friendly platform for buying and selling goods and services
            through auctions. With OfferUp! You can participate in two types of
            auctions: Forward auctions and Dutch auctions.
          </Typography>
        </CardContent>
      </Card>

      <div>
        <img
          src={shoppingbag}
          alt="ShoppingBag"
          style={{
            position: "absolute",
            left: "5%",
            maxWidth: "45%",
          }}
        />
      </div>

      <Card
        sx={{
          height: "550px",
          width: "600px",
          marginTop: "10%",
          marginLeft: "55%",
          backgroundColor: "#ccc390",
          borderRadius: "20px",
        }}
      >
        <CardContent>
          <Typography
            sx={{
              fontFamily: "poppins",
              fontSize: "65px",
              padding: "2%",
            }}
          >
            FORWARD VS DUTCH
            <br />
          </Typography>

          <Typography
            sx={{
              fontFamily: "poppins",
              fontSize: "20px",
              padding: "1%",
            }}
          >
            In forward auctions, sellers offer items for sale and the auctioneer
            receives bids on the item until a fixed amount of time has elapsed.
            The highest bidder at the end of the auction wins the item. In Dutch
            auctions, the auctioneer starts with a high asking price and lowers
            it until a bidder accepts the price or the item reaches a
            predetermined reserve price. The first bidder to accept the price
            wins the auction.
          </Typography>
        </CardContent>
      </Card>

      <div>
        <img
          src={goodies}
          alt="Goodies"
          style={{
            position: "absolute",
            left: "50%",
            marginTop: "8%",
            maxWidth: "45%",
          }}
        />
      </div>

      <Card
        sx={{
          height: "570px",
          width: "600px",
          marginTop: "5%",
          marginLeft: "10%",
          backgroundColor: "#fce5fd",
          borderRadius: "20px",
        }}
      >
        <CardContent>
          <Typography
            sx={{
              fontFamily: "poppins",
              fontSize: "55px",
              padding: "2%",
            }}
          >
            Your Guide to Auction Success!
          </Typography>

          <Typography
            sx={{
              fontFamily: "poppins",
              fontSize: "20px",
              padding: "1%",
            }}
          >
            At OfferUp, we make bidding easy. Browse our catalog of auctioned
            items, search for items using keywords, and select the items you
            want to bid on. Our platform is secure and reliable, handling
            payment and shipping details for you. Once an auction has ended, the
            winner can pay using our secure payment system and the item will be
            shipped to their address. We provide a great experience for both
            buyers and sellers, allowing sellers to easily list and manage their
            auctions and buyers to win great deals on a wide range of goods and
            services. <br />
          </Typography>

          <Typography
            sx={{
              fontFamily: "raleway",
              fontSize: "18px",
              textAlign: "center",
            }}
          >
            Happy Bidding.
          </Typography>
        </CardContent>
      </Card>
    </div>
  );
};

export default AboutPage;
