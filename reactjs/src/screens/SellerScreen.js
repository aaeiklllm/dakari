import React, { useEffect, useState, useRef } from 'react';
import { Link, useLocation, useParams, useNavigate } from 'react-router-dom';

const SellerScreen = () => {
  const [products, setProducts] = useState([]);
  const searchInputRef = useRef(null);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch('http://localhost:8080/getProducts');
        const products = await response.json();
        setProducts(products);
      } catch (error) {
        console.error(error);
      }
    };

    fetchProducts();
  }, []);

  const handleSearch = (e) => {
    e.preventDefault();
    const searchQuery = e.target.elements.search.value;
    console.log('Searching for:', searchQuery);
    navigate(`/seller-search?query=${searchQuery}`);
    searchInputRef.current.value = '';
  };

  const deleteProduct = async (productId) => {
    try {
      await fetch(`http://localhost:8080/deleteProduct/${productId}`);
      const updatedProducts = products.filter((product) => product.product_id !== productId);
      setProducts(updatedProducts);
    } catch (error) {
      console.log('Error occurred:', error);
    }
  };

  const productList = products.map((product) => (
    <div className="product" key={product.product_id}>
      <img src={product.product_image} alt={product.name} />
      <div className="product-info">
        <p>{product.name}</p>
        <p>{product.product_description}</p>
        <p><strong>${product.price}</strong></p>
        <Link to={`/updateProduct/${product.product_id}`}><button className="edit-button">Edit</button></Link>
        <button className="delete-button" onClick={() => deleteProduct(product.product_id)}>Delete</button>
      </div>
    </div>
  ));

  return (
    <div>
      <header className="home-header">
        <Link to="/seller" className="home-logo">
          dakari
        </Link>
        <form onSubmit={handleSearch} className="home-search-form">
          <input
            ref={searchInputRef}
            type="text"
            name="search"
            placeholder="Search"
            className="home-search-input"
          />
          <button type="submit" className="home-search-button">Search</button>
        </form>
        <div className="home-buttons">
          <Link to="/seller-add" className="home-cart-button">
            Add products
          </Link>
          <Link to="/view-sales" className="home-logout-button">
            View sales
          </Link>
          <Link to="/" className="home-logout-button">
            Log-out
          </Link>
        </div>
      </header>
      <div className="product-list">
        {productList}
      </div>
      <style>
        {`
          @import url('https://fonts.googleapis.com/css2?family=Poppins&family=VT323&display=swap');

          body {
            font-family: 'Poppins', sans-serif;
            background-color: #EEEE;
          }

          .product-list {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            margin-top: 20px;
            justify-items: center;
          }

          .product {
            position: relative;
            width: 300px;
            height: 400px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease-in-out;
          }

          .product img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            object-position: center;
            border-radius: 5px;
            overflow: hidden;
          }

          .product-info {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            background-color: rgba(144, 124, 117, 0.8);
            padding: 5px 10px;
            color: #fff;
            font-size: 12px;
            text-align: center;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
            box-sizing: border-box;
          }

          .product-info p {
            margin: 5px 0;
          }

          .product-info strong {
            font-size: 18px;
            font-weight: bold;
          }

          .edit-button {
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            padding: 5px 10px;
            margin-right: 10px;
            cursor: pointer;
            transition: background-color 0.2s ease-in-out;
          }

          .edit-button:hover {
            background-color: #6b5e58;
          }

          .delete-button {
            background-color: #e74c3c;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            padding: 5px 10px;
            margin-right: 10px;
            cursor: pointer;
            transition: background-color 0.2s ease-in-out;
          }

          .delete-button:hover {
            background-color: #c0392b;
          }

          .home-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            background-color: #907C75;
          }

          .home-logo {
            text-decoration: none;
            color: #fff;
            font-size: 24px;
            font-weight: bold;
          }

          .home-search-form {
            display: flex;
            align-items: center;
            flex-grow: 1;
          }

          .home-search-input {
            flex-grow: 1;
            padding: 8px;
            border: none;
            border-radius: 4px;
            margin-right: 10px;
          }

          .home-search-button {
            padding: 8px 16px;
            background-color: #fff;
            color: #907C75;
            border: none;
            border-radius: 4px;
            cursor: pointer;
          }

          .home-buttons {
            display: flex;
            align-items: center;
          }

          .home-cart-button,
          .home-logout-button {
            padding: 8px 16px;
            background-color: transparent;
            color: #fff;
            border: none;
            cursor: pointer;
            margin-left: 10px;
            text-decoration: none;
          }

          .home-cart-button:hover,
          .home-logout-button:hover {
            background-color: rgba(255, 255, 255, 0.2);
          }
        `}
      </style>
    </div>
  );
};

export default SellerScreen;