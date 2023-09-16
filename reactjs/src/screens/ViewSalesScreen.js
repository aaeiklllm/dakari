import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

const BuyScreen = () => {
  const [receipt, setReceipt] = useState(null);

  useEffect(() => {
    const fetchReceipts = async () => {
      try {
        const response = await fetch('http://localhost:8080/getReceipts');
        const receipts = await response.json();
        setReceipt(receipts);
      } catch (error) {
        console.error(error);
      }
    };

    fetchReceipts();
  }, []);

  let receiptList;
  if (receipt) {
    receiptList = receipt.map((receipt) => (
      <div className="receipt" key={receipt.receiptId}>
        <div className="receipt-info">
          <p>{receipt.name}</p>
          <p>{receipt.username}</p>
          <p><div className="receipt-info" dangerouslySetInnerHTML={{ __html: receipt.details }} /></p>
          <p>{receipt.timestamp}</p>
          <br></br>
        </div>
      </div>
    ));
  }

  return (
    <div>
      <header className="buy-header">
        <Link to="/seller" className="back-link">
          Back
        </Link>
      </header>
      <div className="receipt-list">{receiptList}</div>
      <style>
        {`
          /* Paste the CSS code here */
          .buy-header {
            background-color: #907C75;
            padding: 10px;
          }

          .back-link {
            text-decoration: none;
            color: #fff;
            font-size: 18px;
            font-weight: bold;
          }

          .receipt-list {
            margin-top: 20px;
          }

          .receipt {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
          }

          .receipt-info {
            font-size: 16px;
          }
        `}
      </style>
    </div>
  );
};

export default BuyScreen;