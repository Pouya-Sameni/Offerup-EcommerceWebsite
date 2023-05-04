import React from "react";
import favicon from "../assets/favicon.png";
import LoginForm from "../components/Login/LoginForm";
import shopTransparentCrop from "../assets/shopTransparentCrop.png";

const styles = {
  container: "w-screen h-screen grid grid-rows-2 md:grid-cols-2 sm:grid-cols-1",
  leftPanel:
    "w-full h-full bg-black md:h-screen flex justify-center items-center",
  rightPanel: "w-full h-full  bg-black md:h-screen flex justify-end",
};

const LandingPage = () => {
  return (
    <React.Fragment>
      <section>
        <div className={styles.container}>
          {/* leftPanel */}
          <div className={styles.leftPanel}>
            <img
              src={favicon}
              alt="IconLogo"
              style={{
                position: "absolute",
                top: "3%",
                left: "2%",
              }}
            />

            <div
              style={{
                position: "absolute",
                top: "25%",
                left: "5%",
              }}
            >
              <h1 className="flex-1 font-poppins font-semibold text-[54px] text-white">
                <span className="text-babyorange">Bid</span> More,{" "}
                <span className="text-babygreen">Win</span> More!
              </h1>
              <p className="flex-1 font-poppins ss:text-[18px] text-white">
                Looking for a new way to shop online? Join our auction
                <br />
                community and bid on items in real time! With our <br />
                Forward and Dutch auctions, you'll have a chance <br />
                to get the products you love at competitive prices. <br />
                <br />
                <span className="text-white font-semibold">
                  Don't wait, start bidding today!
                </span>
              </p>
            </div>

            <div>
              <img
                src={shopTransparentCrop}
                alt="Shop"
                style={{
                  position: "absolute",
                  top: "17%",
                  left: "30%",
                  maxWidth: "35%",
                }}
              />
            </div>
          </div>
          {/* rightPanel */}
          <div className={styles.rightPanel}>
            <LoginForm />
            <div className="text-white">
              <p className="font-poppins text-3xl mr-10 mt-8">OfferUp!</p>
            </div>
            {/* <img src={landing} alt="Landing" /> */}
          </div>
        </div>
      </section>
    </React.Fragment>
  );
};

export default LandingPage;
