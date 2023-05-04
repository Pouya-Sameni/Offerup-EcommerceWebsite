import axios from "axios";
import Cookies from "js-cookie";

import env from './env';
const BASE_URL = env.BASE_URL;
export async function forwardBid(bidAmount, auctionId) {
  const sessionToken = Cookies.get("sessionToken");
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };

  
  const bid = {
    auctionId: auctionId,
    bidAmount: parseFloat(bidAmount),
    userId: sessionToken,
  };

  try {
    const response = await axios.post(
      `${BASE_URL}auctions/forward/bid?sessionToken=${sessionToken}`,
      bid,
      config
    );

    return response.data;
  } catch (error) {
    //return "Unsuccessfull"
    console.error(error);
  }
}

export async function dutchBuy(auctionId) {
  const sessionToken = Cookies.get("sessionToken");
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };

  
  try {
    const response = await axios.get(
      `${BASE_URL}auctions/dutch/buy?auctionId=${auctionId}&sessionToken=${sessionToken}`,
      config
    );

    return response.data;
  } catch (error) {
    //return "Unsuccessfull"
    console.error(error);
  }
}
