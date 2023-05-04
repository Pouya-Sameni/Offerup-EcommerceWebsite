import React from "react";

import favicon from "../assets/favicon.png";
import signUpPage from "../assets/signUpPage.png";


import SignUp from "../components/Login/SignupForm";

const styles = {
  container: "w-screen h-screen grid grid-rows-2 md:grid-cols-2 sm:grid-cols-1",
  leftPanel: "w-full h-full bg-black md:h-screen flex justify-center items-center",
  rightPanel: "w-full h-full  bg-black md:h-screen flex justify-end",

};



const SignupPage = () => {
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
                left: "2%" 
             }} 
             />

             <div style={{ 
                position: "absolute", 
                top: "20%", 
                left: "5%" }}>
                    <h1 className="flex-1 font-poppins font-semibold text-[54px] text-white">
                    <span className="text-babyorange">Bid</span> More, <span className="text-babygreen">Win</span> More!
                   </h1>
                   <p className="flex-1 font-poppins ss:text-[18px] text-white">
                   Join our auction community and bid in real time! Get the products <br/>
                   you love at competitive prices with our Forward and Dutch auctions.<br/>

                   <br/>
                   <span className="text-white font-semibold">Sign up now and start bidding!</span>
    
                   </p>
                  

             </div>

             <div >
              <img 
                className="max-w-full max-h-full object-contain"
                src={signUpPage} 
                alt="Shop" 
                style={{ 
                  position: "absolute", 
                  top: "0%", 
                  objectFit: "contain",
                  left: "10%" 
                }}
                />
             </div>
          </div>
          {/* rightPanel */}
          <div className={styles.rightPanel}>
              <SignUp />
            <div className="text-white">
                <p className="font-poppins text-3xl mr-10 mt-8">
                  OfferUp!
                </p>
              </div>
          </div>
        </div>
      </section>
    </React.Fragment>
  );
};

export default SignupPage;
