package com.example.MachineProblem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MachineProblem.model.Product;
import com.example.MachineProblem.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public Product getProductId(int id){
		return productRepository.findById(id).get();
	}
	

	public Product updateProduct(int id, Product productDetails) {
		Product product = getProductId(id);
		
		product.setProduct_name(productDetails.getProduct_name());
		product.setProduct_description(productDetails.getProduct_description());
		product.setProduct_image(productDetails.getProduct_image());
		product.setPrice(productDetails.getPrice());
		
		return productRepository.save(product);
	}
	
	public void deleteProduct(int id){
		productRepository.deleteById(id);
	}
	
	public List<Product> searchProduct(String keyword){
		return productRepository.findByProductNameContainingIgnoreCase(keyword);
	}
}