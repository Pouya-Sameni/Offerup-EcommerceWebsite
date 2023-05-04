import axios from 'axios';
import Cookies from "js-cookie";
import env from './env';
const BASE_URL = env.BASE_URL;
const axiosInstance = axios.create({
    headers: {
        "Content-type": "application/json",
    },
 })


export const signin = async (logincredentials) => {
    try {
      const response = await axiosInstance.post(`${BASE_URL}accounts/signin`, logincredentials);
      
      Cookies.set("sessionToken", response.data)
      
      return "Successfull";
    } catch (error) {
        //return "Unsuccessfull"
      console.error(error);
    }
  };


  export const signup = async (formData) => {
    try {
      const response = await axiosInstance.post(`${BASE_URL}accounts/signup`, formData);
      
      Cookies.set("sessionToken", response.data)
      
      return "Successfull";
    } catch (error) {
        //return "Unsuccessfull"
      console.error(error);
    }
  };

