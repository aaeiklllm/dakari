import React, { useEffect, useState, useRef } from 'react';
import { Link, useLocation, useParams, useNavigate } from 'react-router-dom';

const SearchScreen = () => {
  const [products, setProducts] = useState([]);
  const location = useLocation();
  const searchQuery = new URLSearchParams(location.search).get('query');
  const keyword = searchQuery?.split('/')[0]; // Extract the keyword before the slash
  const navigate = useNavigate();

  const { customerId } = useParams(); // Retrieve the username from the URL

  const searchInputRef = useRef(null);

  const handleSearch = (e) => {
    e.preventDefault();
    const searchQuery = e.target.elements.search.value;
    console.log('Searching for:', searchQuery);
    navigate(`/search/${searchQuery}/${customerId}`);
    searchInputRef.current.value = '';
  };

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch(`http://localhost:8080/searchProduct?keyword=${keyword}`);
        const data = await response.json();

        if (response.ok) {
          setProducts(data);
        } else {
          console.error('Error fetching products:', data);
        }
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    };

    if (keyword) {
      fetchProducts();
    }
  }, [keyword]);

  return (
    <div>
    <header className='home-header'>
      <Link to={`/home/${customerId}`}>
        Back
      </Link>
    
    </header>
    <h1>Search Product Results</h1>
    {products.length > 0 ? (
      <ul>
        {products.map((product) => (
          <div className="product" key={product.product_id}>
            <img src={product.product_image} alt={product.name} />
            <div className="product-info">
              <p>{product.name}</p>
              <p>{product.product_description}</p>
              <p>
                <strong>${product.price}</strong>
              </p>
            </div>
          </div>
        ))}
      </ul>
    ) : (
      <p>No products found.</p>
    )}
  </div>
  );
};

export default SearchScreen;
