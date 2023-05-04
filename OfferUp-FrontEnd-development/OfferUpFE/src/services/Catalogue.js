import axios from "axios";
import Cookies from "js-cookie";

import env from './env';
const BASE_URL = env.BASE_URL;
export async function searchForProduct(itemDescription) {
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };
  const sessionToken = Cookies.get("sessionToken");

  try {

    const response = await axios.get(
      `${BASE_URL}catalogue/search/description?description=${itemDescription}&sessionToken=${sessionToken}`,
      config
    );
    
    return response.data;
  } catch (error) {
      //return "Unsuccessfull"
    console.error(error);
  }

}

export async function getProductDetails(itemId) {
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };
  const sessionToken = Cookies.get("sessionToken");

  try {

    const response = await axios.get(
      `${BASE_URL}catalogue/search/id?itemId=${itemId}&sessionToken=${sessionToken}`, 
      config
    );
    
    return response.data;
  } catch (error) {
      //return "Unsuccessfull"
    console.error(error);
  }


  
}

