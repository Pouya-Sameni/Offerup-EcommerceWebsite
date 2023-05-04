import React from "react";
import NavBar from "../components/Bar/NavBar";
import SearchBar from "../components/SearchBar/SearchBar";
import Cookies from "js-cookie";
import laptop from "../assets/laptop.jpg";

const styles = {
  container: "w-screen h-screen grid grid-rows-2 md:grid-cols-2 sm:grid-cols-1",
  leftPanel:
    "w-full h-full bg-black md:h-screen flex justify-center items-center",
  rightPanel: "w-full h-full  bg-black md:h-screen flex justify-end",
};

const HomePage = () => {
  const sessionToken = Cookies.get("sessionToken");

  if (!sessionToken) {
    window.location.href = "/404";
    return null;
  }

  return (
    <div>
      <div>
        <NavBar />
      </div>

      <div>
        <img
          src={laptop}
          alt="Laptop"
          style={{
            objectFit: "cover",
            position: "absolute",
            maxWidth: "100%",
          }}
        />
      </div>

      <div
        style={{
          position: "absolute",
          top: "30%",
          left: "32%",
        }}
      >
        <SearchBar />
      </div>


    </div>
  );
};

export default HomePage;
