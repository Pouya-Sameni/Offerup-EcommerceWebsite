import axios from "axios";
import Cookies from "js-cookie";

import env from './env';
const BASE_URL = env.BASE_URL;

export async function getPaymentPage(auctionId) {
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };
  const sessionToken = Cookies.get("sessionToken");

  try {
     console.log(`${BASE_URL}payment/page/${auctionId}?sessionToken=${sessionToken}`);
    const response = await axios.get(
      `${BASE_URL}payment/page/${auctionId}?sessionToken=${sessionToken}`,
      config
    );
    
    return response.data;
  } catch (error) {
      //return "Unsuccessfull"
    console.error(error);
  }

}

export async function payForAuction(paymentObject) {
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };
  const sessionToken = Cookies.get("sessionToken");

  try {
   
    const response = await axios.post(
      `${BASE_URL}payment/pay/${paymentObject.auctionId}?sessionToken=${sessionToken}`,
      paymentObject.paymentInfo,
      config
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
      //return "Unsuccessfull"
    console.error(error);
  }

}



