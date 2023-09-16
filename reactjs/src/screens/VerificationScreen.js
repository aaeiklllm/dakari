import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import '../styles/VerificationScreen.css';

const VerificationScreen = () => {
  const [verificationCode, setVerificationCode] = useState('');
  const navigate = useNavigate(); // Initialize the useNavigate hook

  const handleVerificationCodeChange = (e) => {
    setVerificationCode(e.target.value);
  };

  const handleVerifyEmail = async (e) => {
    e.preventDefault();
    

    try {
      // Make an API request to fetch the token from the backend
      const response = await fetch(`http://localhost:8080/api/tokens/${verificationCode}`);
      const token = await response.text();
      console.log(token);
      console.log(verificationCode);

      // Compare the token with the verification code
      if (verificationCode === token) {
        // Redirect to the login page
        const link = "http://localhost:8080/confirm?token=" + verificationCode;
    window.open(link, '_blank'); // Open the link in a new tab
        navigate('/login');
      } else {
        // Handle invalid verification code
        window.alert('Invalid verification code');
      }
    } catch (error) {
      // Handle error from the API request
      console.error(error);
      window.alert('Error occurred while verifying the code');
    }
  };

  return (
    <div className="signup-container">
      <h1>Email Verification</h1>
      <p>Check your email inbox for the verification code and link.</p>
      <form onSubmit={handleVerifyEmail}>
        <input
          type="text"
          placeholder="Verification Code"
          value={verificationCode}
          onChange={handleVerificationCodeChange}
        />
        <br />
        <button type="submit" className="verification-button smaller-button">Verify code</button>
        <Link to={"/login"}>
        <button className="verification-button smaller-button">Already verified through link</button>
      </Link>
      </form>
    </div>
  );
};

export default VerificationScreen;