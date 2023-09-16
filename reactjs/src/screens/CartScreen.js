import { Link, useParams, useNavigate } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import '../styles/CartScreen.css';

const CartScreen = () => {
  const { customerId } = useParams();
  const [cart, setCart] = useState([]);
  const navigate = useNavigate();
  const [productNames, setProductNames] = useState([]);
  const [receiptDetails, setReceiptDetails] = useState([]);
  const [receiptId, setReceiptId] = useState('');

  const fetchProductDetails = async (productId) => {
    const response = await fetch(`http://localhost:8080/product/${productId}`);
    const product = await response.json();
    return product; 
  };

  const getProductNames = async () => {
    const details = await Promise.all(
      cart.map((cartItem) => fetchProductDetails(cartItem.product.product_id))
    );
    const names = details.map((detail) => detail.product_name);
    setProductNames(names);
  };  

  useEffect(() => {
    const fetchCartItems = async () => {
      const response = await fetch(`http://localhost:8080/displayCart?customerId=${customerId}`);
      const cart = await response.json();
      setCart(cart);
    };

    fetchCartItems();
  }, [customerId]);

  useEffect(() => {
    getProductNames();
  }, [cart]);

  useEffect(() => {
    const generateReceiptDetails = () => {
      const details = cart.map((cartItem) => {
        return {
          productName: cartItem.product.product_name,
          quantity: cartItem.quantity,
        };
      });
      setReceiptDetails(details);
    };

    generateReceiptDetails();
  }, [cart]);

  const updateCartItemQuantityInDatabase = (cartId, quantity) => {
    fetch(`http://localhost:8080/update-quantity?cartId=${cartId}&quantity=${quantity}`, {
      method: 'POST',
    })
      .then(response => {
        if (response.ok) {
          console.log("Cart item quantity updated in the database");
        } else {
          throw new Error("Failed to update cart item quantity in the database");
        }
      })
      .catch(error => {
        console.error(error);
      });
  };

  const decreaseQuantity = (cartId) => {
    const cartItem = cart.find(item => item.cart_id === cartId);
    if (cartItem.quantity > 1) {
      const updatedCart = [...cart];
      const index = updatedCart.findIndex(item => item.cart_id === cartId);
      if (index !== -1) {
        updatedCart[index].quantity--;
        setCart(updatedCart);
        updateCartItemQuantityInDatabase(cartId, updatedCart[index].quantity);
      }
    }
  };

  const increaseQuantity = (cartId) => {
    const cartItem = cart.find(item => item.cart_id === cartId);
    const updatedCart = [...cart];
    const index = updatedCart.findIndex(item => item.cart_id === cartId);
    if (index !== -1) {
      updatedCart[index].quantity++;
      setCart(updatedCart);
      updateCartItemQuantityInDatabase(cartId, updatedCart[index].quantity);
    }
  };

  const deleteCartItem = async (cartId) => {
    try {
      await fetch(`http://localhost:8080/deleteCartItem/${cartId}`);
      const updatedCart = cart.filter((item) => item.cart_id !== cartId);
      setCart(updatedCart);
    } catch (error) {
      console.log('Error occurred:', error);
    }
  };

  const generateReceipt = async (receiptDetails) => {
    const receiptDetailsHtml = `
      <table>
        <tr>
          <th>Item</th>
          <th>Quantity</th>
        </tr>
        ${receiptDetails
          .map(
            (item) => `
              <tr>
                <td>${item.productName}</td>
                <td>${item.quantity}</td>
              </tr>
            `
          )
          .join('')}
      </table>`;

    try {
      const response = await fetch(`http://localhost:8080/receipt?customerId=${customerId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'text/plain',
        },
        body: receiptDetailsHtml,
      });

      if (response.ok) {
        const receiptId = await response.text();
        setReceiptId(receiptId); 
        navigate(`/buy/${receiptId}`);
      } else {
        console.error('Failed to generate receipt');
      }
    } catch (error) {
      console.error('Error occurred:', error);
    }
  };

  const deleteCartByUser= async (customerId) => {
    await fetch(`http://localhost:8080/deleteCartByUser?username=${customerId}`);
  }

  const cartList = cart.map((cartItem, idx) => {
    const price = cartItem.product.price * cartItem.quantity;

    return (
      <div className="cart-item" key={idx}>
        <div className="cart-item-info">
          <p className="cart-item-name">{cartItem.product.product_name}</p>
          <img className="cart-item-image" src={cartItem.product.product_image} alt={cartItem.product.name} />
          <p className="cart-item-price">${cartItem.product.price}</p>
          <div className="cart-item-quantity">
            <button onClick={() => decreaseQuantity(cartItem.cart_id)}>-</button>
            <p>{cartItem.quantity}</p>
            <button onClick={() => increaseQuantity(cartItem.cart_id)}>+</button>
          </div>
          <p className="cart-item-total-price">${price}</p>
        </div>
        <button className="cart-item-delete" onClick={() => deleteCartItem(cartItem.cart_id)}>Delete</button>
      </div>
    );
  });

  const totalPrice = cart.reduce((total, cartItem) => {
    const productPrice = cartItem.product.price;
    const quantity = cartItem.quantity;
    const itemTotalPrice = productPrice * quantity;
    return total + itemTotalPrice;
  }, 0);

  return (
    <div className="cart-screen">
     <header className="home-header">
      <Link to={`/home/${customerId}`} className="back-link">
        Back
      </Link>
      <Link to={`/cart/${customerId}`} className="cart-link">
        <button className="cart-button">Cart</button>
      </Link>
      <Link to="/" className="logout-link">
        Log-out
      </Link>
    </header>
      <h1 className="cart-title">{customerId}'s Cart</h1>
      <div className="cart-items">{cartList}</div>
      <p className="total-price">Total Price: ${totalPrice}</p>
    
      <button className="buy-button" onClick={() => {
        generateReceipt(receiptDetails);
        deleteCartByUser(customerId);
      }}>Buy Products</button>
    </div>
  );
};

export default CartScreen;