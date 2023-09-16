import React, { useEffect, useState, useRef } from 'react';
import { Link, useLocation, useParams, useNavigate } from 'react-router-dom';
import '../styles/HomeScreen.css';

const HomeScreen = () => {
  const { customerId } = useParams();
  const [product, setProduct] = useState([]);
  const [cart, setCart] = useState([]);
  const navigate = useNavigate();
  const location = useLocation();

  // Extract the username from the current URL
  const username = location.pathname.split('/')[2];
  const searchInputRef = useRef(null);

  const handleSearch = (e) => {
    e.preventDefault();
    const searchQuery = e.target.elements.search.value;
    // Implement your search functionality here
    console.log('Searching for:', searchQuery);
    // Redirect or perform search operation
    navigate(`/search?query=${searchQuery}/${username}`);
    searchInputRef.current.value = ''; // Empty the search bar
  };

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch('http://localhost:8080/getProducts');
        const products = await response.json();
        setProduct(products);
      } catch (error) {
        console.error(error);
      }
    };

    fetchProducts();
  }, []);

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        const response = await fetch(`http://localhost:8080/displayCart?customerId=${customerId}`);
        const cart = await response.json();
        setCart(cart);
      } catch (error) {
        console.error(error);
      }
    };

    fetchCartItems();
  }, [customerId]);

  const handleButtonClick = async (productObj) => {
    try {
      const response = await fetch(`http://localhost:8080/add-to-cart?customerId=${customerId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(productObj),
      });

      if (response.ok) {
        const updatedCart = [...cart, productObj];
        setCart(updatedCart);
      } else {
        throw new Error('Failed to add item to cart');
      }
    } catch (error) {
      console.error(error);
    }
  };

  const productList = product.map((product) => {
    return (
      <div className={`product ${cart.includes(product) ? 'add-to-cart-animation' : ''}`} key={product.product_id}>
        <img src={product.product_image} alt={product.name} />
        <div className="product-info">
          <p>{product.name}</p>
          <p>{product.product_description}</p>
          <p>
            <strong>${product.price}</strong>
          </p>
          <button className="AddtoCart" onClick={() => handleButtonClick(product)}>Add to cart</button>
        </div>
      </div>
    );
  });

  return (
    <div>
      
      <header className="home-header">
        {/* <Link to={`/home/${customerId}`} className="home-logo">
          products 
        </Link> */}
        <form onSubmit={handleSearch} className="home-search-form">
          <input
            ref={searchInputRef}
            type="text"
            name="search"
            placeholder="Search"
            className="home-search-input"
          />
          <button type="submit" className="home-search-button">
            Search
          </button>
        </form>
        <div className="home-buttons">
          <Link to={`/cart/${customerId}`} className="home-cart-button">
            Cart
          </Link>
          <Link to="/" className="home-logout-button">
            Log-out
          </Link>
        </div>
      </header>
      <img class="head" src="https://cdn.discordapp.com/attachments/775346740122484750/1115923457201414186/ads.gif" />     

      <div className="product-list">{productList}</div>
    </div>
  );
};

export default HomeScreen;