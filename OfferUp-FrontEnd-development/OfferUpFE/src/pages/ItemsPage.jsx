import React from "react";
import NavBar from "../components/Bar/NavBar";
import ItemTable from "../components/Table/ItemTable";
import { useLocation } from "react-router-dom";
import Cookies from "js-cookie";
import { useState, useEffect } from "react";
import { searchForProduct } from "../services/Catalogue";

const ItemsPage = () => {
  const { state } = useLocation();
  const [auctionInfo, setAuctionInfo] = useState([]);
  const sessionToken = Cookies.get("sessionToken");

  useEffect(() => {
    handleSearch();
  }, []);

  useEffect(() => {}, [auctionInfo]);
  
  const handleSearch = async () => {
    const auctionItems = await searchForProduct(state);
    setAuctionInfo(auctionItems);
  };

  if (!sessionToken) {
    window.location.href = "/404";
    return null;
  }

  return (
    <div>
      <div>
        <NavBar />
      </div>
      <h1
        style={{
          fontFamily: "poppins",
          fontSize: 54,
          fontWeight: "bold",
          color: "#85b5bf",
          textAlign: "center",
        }}
      >
        Pick the Auction of Your Choice...
      </h1>
      <div
        style={{
          fontFamily: "poppins",
          fontSize: 18,
          color: "",
          textAlign: "center",
        }}
      >
        Here, you can browse through our auctions and pick the one that
        interests you the most.
        <br />
        Want to learn more about a specific item? Simply click on the Preview
        button for additional details.
        <br />
        Happy shopping!
      </div>
      <div>
        <ItemTable auctionData={auctionInfo} />
      </div>
    </div>
  );
};

export default ItemsPage;
