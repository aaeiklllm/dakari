import {BrowserRouter, Link, Routes, Route } from "react-router-dom";
import HomeScreen from "./screens/HomeScreen";
import CartScreen from "./screens/CartScreen";
import SellerAddScreen from "./screens/SellerAddScreen";
import SellerScreen from "./screens/SellerScreen";
import SellerEditScreen from "./screens/SellerEditScreen";
import LoginScreen from "./screens/LoginScreen";
import SignUpScreen from "./screens/SignUpScreen";
import LandingScreen from "./screens/LandingScreen";
import BuyScreen from "./screens/BuyScreen";
import ViewSalesScreen from "./screens/ViewSalesScreen";
import BuyerSearchScreen from  "./screens/BuyerSearchScreen";
import SellerSearchScreen from  "./screens/SellerSearchScreen";
import VerificationScreen from  "./screens/VerificationScreen";

function App() {
  return (
      <div>
         <BrowserRouter>
          <Routes>
          <Route path="/" element={<LandingScreen />} />
          <Route path="/cart/:customerId" element={<CartScreen />} />
          <Route path="/seller" element={<SellerScreen />} />
          <Route path="/home/:customerId" element={<HomeScreen />} />
          <Route path="/seller-add" element={<SellerAddScreen />} />
          <Route path="/updateProduct/:productId" element={<SellerEditScreen />} />
          <Route path="/seller-view-orders" element={<viewScreen />} />
          <Route path="/login" element={<LoginScreen/>} /> 
          <Route path="/signup" element={<SignUpScreen />} />
          <Route path="/buy/:receiptId" element={<BuyScreen />} />
          <Route path="/view-sales" element={<ViewSalesScreen />} />

          <Route path="/search" element={<BuyerSearchScreen />} />
          <Route path="/seller-search" element={<SellerSearchScreen />} />

          <Route path="/verification" element={<VerificationScreen />} />
        </Routes> 
        </BrowserRouter>
      </div>
  
  );
}

export default App;
