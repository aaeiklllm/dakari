package com.example.MachineProblem.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MachineProblem.model.CartItem;
import com.example.MachineProblem.model.Customer;
import com.example.MachineProblem.model.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	public List<CartItem> findByCustomer(Customer customer);
	public CartItem findByCustomerAndProduct(Customer customer, Product product);
	public CartItem findByCartId(int cartId);
	void deleteByCustomerUsername(String username);


}