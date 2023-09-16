import React from 'react'
import { Link } from 'react-router-dom'
import '../styles/SellerAddScreen.css'; // Import custom CSS file for styling

class SellerAddScreen extends React.Component{
    constructor(props){
        super(props)

    this.state = {
        product: {
            product_id:"",
            product_name:"",
            product_description:"",
            product_image:"",
            price:""
            }
        }

        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(e){
        let target = e.target
        let value = target.value
        let name = target.name
        let temp = this.state.product
        
        if (name === 'price') {
            if (!isNaN(value)) {
            // Convert the value to a number
            temp[name] = parseFloat(value);
            } else {
            alert('Price must be a number');
            target.value = ''; // Clear the input value
            return;
            }
        } else {
            // For other fields, assign the value as it is
            temp[name] = value;
        }

        console.log(temp);
        this.setState({ product: temp });
    }

    handleSubmit(e) {
        e.preventDefault();
        const keyword = this.state.product.product_name;
      
        // Check if the product name is already existing
        fetch(`http://localhost:8080/searchProduct?keyword=${encodeURIComponent(keyword)}`)
          .then(response => response.json())
          .then(data => {
            if (data.length > 0) {
              alert('Product already exists.');
            } else {
              // Product name is unique, proceed with adding the product
              const product = this.state.product;
              fetch('http://localhost:8080/addProducts', {
                headers: { 
                  'Accept': 'application/json',
                  'Content-Type': 'application/json' 
                },
                method: "POST",
                body: JSON.stringify(product)
              })
              .then(response => {
                if (response.ok) {
                  // Product added successfully
                  console.log('Product added successfully.')
                  //alert('Product added successfully.');
                  // Reset the form
                  this.setState({
                    product: {
                      product_id: "",
                      product_name: "",
                      product_description: "",
                      product_image: "",
                      price: ""
                    }
                  });
                } else {
                  // Error adding the product
                  //alert('Error adding the product.');
                }
              })
              .catch(error => {
                console.log(error);
                //alert('Error adding the product.');
              });
            }
          })
          .catch(error => {
            console.log(error);
            //alert('Error checking product name existence.');
          });
      }

    render(){
        return(
          <div className="seller-add-screen">

          <h1>Add Products</h1>
          <form>
            <label htmlFor="product_name">Product Name:</label>
            <input
              type="text"
              name="product_name"
              id="product_name"
              onChange={this.handleChange}
            />
            <br />
            <label htmlFor="product_description">Product Description:</label>
            <input
              type="text"
              name="product_description"
              id="product_description"
              onChange={this.handleChange}
            />
            <br />
            <label htmlFor="product_image">Image URL:</label>
            <input
              type="text"
              name="product_image"
              id="product_image"
              onChange={this.handleChange}
            />
            <br />
            <label htmlFor="price">Price:</label>
            <input
              type="text"
              name="price"
              id="price"
              onChange={this.handleChange}
            />
            <br />
            <button onClick={this.handleSubmit} className=" back-button">
              Submit
            </button>
            <Link to="/seller" className="back-button">
              Back
            </Link>
          </form>
        </div>
        )
    }
}

export default SellerAddScreen;