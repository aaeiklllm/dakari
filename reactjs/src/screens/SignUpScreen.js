import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/SignUpScreen.css';

const SignUpScreen = () => {
  const initialCustomerState = {
    username: '',
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    userType:'', //for buyer or seller 
  };

  const [customer, setCustomer] = useState(initialCustomerState);
  const navigate = useNavigate(); // Initialize the useNavigate hook
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCustomer((prevCustomer) => ({
      ...prevCustomer,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

        // Email validation
        const emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+([a-zA-Z]{2,7}|[0-9]{1,3})$/;
        const isValidEmail = emailRegex.test(customer.email);
        if (!isValidEmail) {
          window.alert('Invalid email format');
          return;
        }

         // Password validation
        if (customer.password.length < 7) {
          window.alert('Password must be at least 7 characters long');
          return;
        }

        // Set the userType based on the clicked button
        const userType = e.target.innerText === 'Buyer' ? 'buyer' : 'seller';

        // Add the userType to the customer object
        const updatedCustomer = {
          ...customer,
          userType,
        };

        console.log(updatedCustomer);

    fetch('http://localhost:8080/addSignUp', {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      method: 'POST',
      body: JSON.stringify( updatedCustomer ),
     // body: JSON.stringify( username, email ),
    })
      .then((response) => {
        if (response.ok) {
            // Reset the input fields to empty values
            setCustomer(initialCustomerState);
            // Redirect to the login page
            //navigate(`/home/${username}`); 
            //navigate('/login');

            navigate('/verification');
        } else {
          //setError('Taken username or email');
          window.alert('Taken username or email');
        }
   
      })
      .catch((error) => {
        console.error('Error: Taken username or email', error);
      });
  };

  return (
    <div className="signup-container">
      <h1>SIGN UP</h1>
      <form className="signup-form">
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          name="username"
          id="username"
          value={customer.username}
          onChange={handleChange}
        />
        <br />
        <label htmlFor="firstName">First Name:</label>
        <input
          type="text"
          name="firstName"
          id="firstName"
          value={customer.firstName}
          onChange={handleChange}
        />
        <br />
        <label htmlFor="lastName">Last Name:</label>
        <input
          type="text"
          name="lastName"
          id="lastName"
          value={customer.lastName}
          onChange={handleChange}
        />
        <br />
        <label htmlFor="email">Email:</label>
        <input
          type="text"
          name="email"
          id="email"
          value={customer.email}
          onChange={handleChange}
        />
        <br />
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          name="password"
          id="password"
          value={customer.password}
          onChange={handleChange}
        />
        <br />
        <div className="signup-buttons">
          <button className="signup-button smaller-button" onClick={handleSubmit}>
            Buyer
          </button>
          <button className="signup-button smaller-button" onClick={handleSubmit}>
            Seller
          </button>
        </div>
        <button onClick={() => navigate('/')}>Home</button>
      </form>
    </div>
  );
};

export default SignUpScreen;