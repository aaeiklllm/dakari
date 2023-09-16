package com.example.MachineProblem.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.MachineProblem.model.CartItem;

import com.example.MachineProblem.repository.CartItemRepository;

import jakarta.transaction.Transactional;

@Service
public class CartItemService {
	
	@Autowired
	CartItemRepository cartRepo;

	public CartItem getCartItemByCartId(int cartId) {
	    return cartRepo.findById(cartId).get();
	}
	
	public void deleteCartItem(int cartId){
		cartRepo.deleteById(cartId);
	}
	
	@Transactional
	public void deleteCartByUser(String username) {
	    cartRepo.deleteByCustomerUsername(username);
	}



}