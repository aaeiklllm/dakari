import { Link, useParams } from 'react-router-dom';
import React, { useEffect, useState } from 'react';

const BuyScreen = () => {
  const { receiptId } = useParams();
  const [receipt, setReceipt] = useState(null);

  useEffect(() => {
    const fetchReceipt = async () => {
      try {
        const response = await fetch(`http://localhost:8080/getReceipt/${receiptId}`);
        const generatedReceipt = await response.text();
        setReceipt(generatedReceipt);
      } catch (error) {
        console.error(error);
      }
    };

    fetchReceipt();
  }, []);

  return (
    <div>
      <h1>Thank you for your purchase!</h1>
      <p>Order #: {receiptId}</p>
      <div className="receipt">
        <div className="receipt-info" dangerouslySetInnerHTML={{ __html: receipt }} />
      </div>
      <Link to={"/"}>
        <button>Log-out</button>
      </Link>
      <style>
        {`
          .receipt {
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 20px;
            margin-top: 20px;
          }

          .receipt-info {
            font-family: Arial, sans-serif;
            font-size: 14px;
            line-height: 1.5;
          }
        `}
      </style>
    </div>
  );
};

export default BuyScreen;