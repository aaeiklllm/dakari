import React,{ createContext, useContext, useReducer } from "react";

//creating the  cart context...
let CartContext = createContext();

//initial state of cart
export const cartState = {
    cartItems:[{product_id: 3, product_name: "Basic Gray Tee", product_description: "A basic grayish tee", product_image: "/images/p3.jpg", in_stock: 6}]
}

//for cart adding and updating...
export const reducer=(state,action)=>{
    switch(action.type){
        case "add_cart":
        return {cartItems:[...action.data]}
        break;
        case "delete_cart":
        break;
        default:
            return state;
    }
}

//cart context provider 
export const ContextProvider=({cartState,reducer,children})=>{
  return (
    <CartContext.Provider value={useReducer(reducer,cartState)}>
    {children}
</CartContext.Provider>
  )
}
//accssing the cart context inside the components..
export const CartContextValue = () => useContext(CartContext);