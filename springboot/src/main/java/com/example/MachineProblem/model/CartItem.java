package com.example.MachineProblem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "cart_items")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;

	@ManyToOne
	@JoinColumn(name = "username")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;


	public Integer getID() {
		return cartId;
	}


	public Integer getCart_id() {
		return cartId;
	}


	public void setCart_id(Integer cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}