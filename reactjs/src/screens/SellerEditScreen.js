import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

const SellerEditScreen = () => {
  const { productId } = useParams();
  const [product, setProduct] = useState({
    product_id: productId,
    product_name: "",
    product_description: "",
    product_image: "",
    price: ""
  });

  useEffect(() => {
    fetch(`http://localhost:8080/product/${productId}`)
      .then(response => response.json())
      .then(data => {
        setProduct(data);
      })
      .catch(error => {
        console.log('Error occurred:', error);
      });
  }, [productId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct(prevProduct => ({
      ...prevProduct,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Validate input data types
    const { product_name, product_description, product_image, price } = product;

    if (typeof product_name !== 'string' || product_name.trim().length === 0) {
      alert('Invalid product name');
      return;
    }

    if (typeof product_description !== 'string' || product_description.trim().length === 0) {
      alert('Invalid product description');
      return;
    }

    if (typeof product_image !== 'string' || product_image.trim().length === 0) {
      alert('Invalid product image URL');
      return;
    }

    const parsedPrice = parseFloat(price);
    if (isNaN(parsedPrice) || parsedPrice <= 0) {
      alert('Invalid price');
      return;
    }

    // All input data is valid, proceed with updating the product
    const updatedProduct = {
      ...product,
      price: parsedPrice
    };

    try {
      fetch(`http://localhost:8080/product/${productId}`, {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        method: "PUT",
        body: JSON.stringify(updatedProduct)
      });
    } catch (error) {
      console.log('Error occurred:', error)
    }
  };

  return (
    <div>
      <h1>Edit Products</h1>
      <form>
        <label htmlFor="product_name">Product Name: </label>
        <input
          type="text"
          name="product_name"
          id="product_name"
          value={product.product_name}
          onChange={handleChange}
        />
        <br />
        <label htmlFor="product_description">Product Description: </label>
        <input
          type="text"
          name="product_description"
          id="product_description"
          value={product.product_description}
          onChange={handleChange}
        />
        <br />
        <label htmlFor="product_image">Image URL: </label>
        <input
          type="text"
          name="product_image"
          id="product_image"
          value={product.product_image}
          onChange={handleChange}
        />
        <br />
        <label htmlFor="price">Price: </label>
        <input
          type="text"
          name="price"
          id="price"
          value={product.price}
          onChange={handleChange}
        />
        <br />
        <button onClick={handleSubmit}>Submit</button>
        <Link to="/seller">
          <button>Back</button>
        </Link>
      </form>
    </div>
  );
};

export default SellerEditScreen;