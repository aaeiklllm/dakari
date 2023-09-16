package com.example.MachineProblem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MachineProblem.model.Product;
import com.example.MachineProblem.model.CartItem;
import com.example.MachineProblem.model.Customer;
import com.example.MachineProblem.repository.CartItemRepository;
import com.example.MachineProblem.service.CartItemService;
import com.example.MachineProblem.service.ProductService;
import com.example.MachineProblem.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class CartItemController {

	@Autowired
	CartItemService cartService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CartItemRepository cartRepo;

	@Autowired
	ProductService productService;

	@PostMapping("/add-to-cart")
	public ResponseEntity<String> addToCart(@RequestBody Product product, @RequestParam("customerId") String customerId) {
		Customer customer = customerService.getCustomerId(customerId);

		CartItem existingCartItem = cartRepo.findByCustomerAndProduct(customer, product);
		if (existingCartItem != null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
			cartRepo.save(existingCartItem);
		}
		else {
			CartItem newCartItem = new CartItem();
			newCartItem.setCustomer(customer);
			newCartItem.setProduct(product);
			newCartItem.setQuantity(1);
			cartRepo.save(newCartItem);
		}

		return ResponseEntity.ok("Product added to cart successfully");
	}
	 
	@GetMapping("/displayCart")
	public ResponseEntity<List<CartItem>> getCartItemsByCustomer(@RequestParam("customerId") String customerId) {
	    Customer customer = customerService.getCustomerId(customerId);
	    List<CartItem> cartItems = cartRepo.findByCustomer(customer);
	    return ResponseEntity.ok(cartItems);
	}
	
	@PostMapping("/update-quantity")
	public ResponseEntity<String> updateCartItemQuantity(@RequestParam("cartId") int cartId, @RequestParam("quantity") int quantity) {
	    CartItem cartItem = cartService.getCartItemByCartId(cartId);
	    cartItem.setQuantity(quantity);
	    cartRepo.save(cartItem);
	    return ResponseEntity.ok("Cart item quantity updated successfully");
	}
	
	@GetMapping("/deleteCartItem/{cartId}")
	public ResponseEntity<Void> deleteCartItem(@PathVariable int cartId){
    	cartService.deleteCartItem(cartId);
    	
    	return ResponseEntity.ok(null);
    }
	
	@GetMapping("/deleteCartByUser")
	public ResponseEntity<Void> deleteCartByUser(@RequestParam("username") String username) {
	    cartService.deleteCartByUser(username);
	    return ResponseEntity.ok().build();
	}



}