
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/LandingScreen.css';

const LandingPage = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/getProducts')
      .then(response => response.json())
      .then(data => {
        setProducts(data);
      })
      .catch(error => {
        console.log('Error occurred:', error);
      });
  }, []);

  return (
    <div>
      <div className="navbar">
        <Link to="/"className="button">dakari</Link>
        <div>
          <Link to="/login" className="button">Login</Link>
          <Link to="/signup" className="button">Sign Up</Link>
        </div>
      </div>
      <img
        className="headr"
        src="https://cdn.discordapp.com/attachments/775346740122484750/1118201807743758446/header_1.gif"
        alt="Header"
      />
      
      <img class="headr" src="https://media.discordapp.net/attachments/775346740122484750/1118210099253628990/titles.png?width=1410&height=128"/>
      <div className="product-grid">
        {products.map(product => (
          <div className="polaroid" key={product.product_id}>
            <img src={product.product_image} alt={product.product_name} />
            <div className="caption">
              <h2>{product.product_name}</h2>
            </div>
          </div>
        ))}
      </div>
      <footer class="footer">
  <h2 class="footer-logo">dakari.</h2>
  <p class="footer-description">A clothing store for fashion enthusiasts</p>
  <div class="footer-links">
  </div>

</footer>
<br></br>
<br></br>
    </div>
    
  );
};

export default LandingPage;