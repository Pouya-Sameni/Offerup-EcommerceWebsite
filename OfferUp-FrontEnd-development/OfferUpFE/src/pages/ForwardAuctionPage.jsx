import React from "react";
import NavBar from "../components/Bar/NavBar";
import ForwardComponent from "../components/Forward/ForwardComponent";
import { useLocation } from "react-router-dom";
import backgroundDutchForward from "../assets/backgroundDutchForward.jpg";
import { useState, useEffect } from "react";
import Cookies from "js-cookie";
import env from "../services/env";
const BASE_URL = env.WS_URL;

function ForwardAuctionPage() {
 

  const location = useLocation();

  

  const [auctionInformation, setAuctionInformation] = useState(
    location.state.auctionInfo
  );
  const [webSocket, setWebSocket] = useState(null);

  useEffect(() => {
    const url = `ws:${BASE_URL}/forward/${auctionInformation.auctionId}/update`;
    const socket = new WebSocket(url);

    socket.onopen = function () {
      console.log("WebSocket connection established");
    };

    socket.onmessage = function (event) {
      try {
        const updatedAuctionInfo = JSON.parse(event.data);

        setAuctionInformation(updatedAuctionInfo);
        console.log("Received message:", updatedAuctionInfo);
      } catch (e) {
        console.log(e);
      }
    };

    socket.onerror = function (error) {
      console.error("WebSocket error:", error);
    };

    setWebSocket(socket);

    return () => {
      console.log("Closing WebSocket connection");
      socket.close();
    };
  }, [auctionInformation.auctionId]);
  return (
    <div style={{ overflow: "hidden" }}>
      <div>
        <NavBar />
      </div>

      <div style={{ position: "relative" }}>
        <img
          src={backgroundDutchForward}
          style={{ height: "100%", width: "100%" }}
        />
        <div
          style={{
            position: "absolute",
            top: 2,
            left: 0,
            width: "100%",
            height: "100%",
          }}
        >
          <div style={{ position: "relative" }}>
            <ForwardComponent
              auctionInfo={auctionInformation}
              itemInfo={location.state.itemInfo}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default ForwardAuctionPage;
