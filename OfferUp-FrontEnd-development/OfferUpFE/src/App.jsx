import React from "react";
import LandingPage from "./pages/LandingPage";
import HomePage from "./pages/HomePage";
import SignupPage from "./pages/SignupPage";
import ItemsPage from "./pages/ItemsPage";
import PaymentPage from "./pages/PaymentPage";
import ForwardAuctionPage from "./pages/ForwardAuctionPage";
import DutchAuctionPage from "./pages/DutchAuctionPage";

import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import AboutPage from "./pages/AboutPage";

const NotFoundPage = () => {
  return (
    <div style={{ display: "flex", alignItems: "center", justifyContent: "center", height: "100vh", fontFamily: "poppins" }}>
      <div style={{ textAlign: "center" }}>
        <h1 style={{ fontSize: "5rem" }}>404 Not Found</h1>
        <p>The page you are looking for does not exist.</p>
      </div>
    </div>
  );
};


export default function () {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="signup" element={<SignupPage />} />
        <Route path="home" element={<HomePage />} />
        <Route path="items" element={<ItemsPage />} />
        <Route path="payment" element={<PaymentPage />} />
        <Route path="about" element={<AboutPage />} />
        <Route path="forwardAuction" element={<ForwardAuctionPage />} />
        <Route path="dutchAuction" element={<DutchAuctionPage />} />

        <Route path="/404" element={<NotFoundPage />} />
      </Routes>
    </Router>
  );
}