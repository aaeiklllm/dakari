import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/LoginScreen.css';

const LoginScreen = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'username') {
      setUsername(value);
    } else if (name === 'password') {
      setPassword(value);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/addLogin', {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify({ username, password })
    })
      .then((response) => {
        console.log(response);
        if (response.ok) {
          return response.text(); // Extract the userType from the response
        } else {
          throw new Error('Account not yet verified');
        }
      })
      .then((userType) => {
        console.log('Login successful');
        if (userType === 'seller') {
          navigate(`/seller`); // Redirect to the seller page
        } else {
          navigate(`/home/${username}`); // Redirect to the buyer's home page
        }
      })
      .catch((error) => {
        if (error.message === 'Account not yet verified') {
          window.alert('Account not yet verified');
        } else {
          window.alert('Invalid username or password');
        }
      });
  };

  return (
    <div className="login-container">
      <h1>Login</h1>
      <form className="login-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input type="text" name="username" id="username" value={username} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input type="password" name="password" id="password" value={password} onChange={handleChange} required />
        </div>
        <button className="login-button" type="submit">Login</button>
        <p className="error-message">{error}</p>
      </form>
    
      <button onClick={() => navigate('/')}>Home</button>
    </div>
  );
};

export default LoginScreen;