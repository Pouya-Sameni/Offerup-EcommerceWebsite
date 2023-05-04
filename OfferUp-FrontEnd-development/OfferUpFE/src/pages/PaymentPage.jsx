import React from "react";
import NavBar from "../components/Bar/NavBar";
import PaymentMain from "../components/Payment/PaymentMain";
import { useLocation } from "react-router-dom";
import { useEffect } from "react";
import { useState } from "react";
import checkout from "../assets/checkout.png";

const styles = {
  container: "w-screen h-screen grid grid-rows-2 md:grid-cols-2 sm:grid-cols-1",
  leftPanel: "w-full h-full md:h-screen flex justify-center items-center",
  rightPanel: "w-full h-full  md:h-screen flex justify-end",
  cardImg: { display: "inline-block" },
  cardGrid: {
    display: "grid",
    gridTemplateColumns: "auto auto",
    gridColumnGap: "-20px",
  },
  card2Img: { display: "inline-block", marginLeft: "-20px", marginTop: "20px" },
};

const PaymentPage = () => {

  const location = useLocation();
  const [renderNavBar, setRenderNavBar] = useState(false);

  const result = location.state.result;
  console.log(result);

  useEffect(() => {
    setRenderNavBar(true);
    console.log(result);

  }, []);

  return (
    // <React.Fragment>
    //   <section>
    <div>
      <div>
        {renderNavBar && <NavBar />}
      </div>
      <div className={styles.container}>
        {/* leftPanel */}
        <div className={styles.leftPanel}>
          <div>
            <PaymentMain result= {result}/>
          </div>
        </div>
        <div>
          <img
            src={checkout}
            alt="Checkout"
            style={{
              position: "absolute",
              left: "60%",
              marginTop: "0%",
              maxWidth: "45%",
            }}
          />
        </div>
      </div>
    </div>
    //   </section>
    // </React.Fragment>
  );
};

export default PaymentPage;
