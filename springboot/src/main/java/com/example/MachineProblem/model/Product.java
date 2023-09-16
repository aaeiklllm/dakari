package com.example.MachineProblem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	@JsonProperty("product_id")
	int product_id;
	
	
	@Column(name = "productName")
	@JsonProperty("productName")
	String productName;
	
	@Column(name = "product_description")
	@JsonProperty("product_description")
	String product_description;

	@Column(name = "product_image")
	@JsonProperty("product_image")
	String product_image;
	
	@Column(name = "price")
	@JsonProperty("price")
	double price;

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return productName;
	}

	public void setProduct_name(String product_name) {
		this.productName = product_name;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
