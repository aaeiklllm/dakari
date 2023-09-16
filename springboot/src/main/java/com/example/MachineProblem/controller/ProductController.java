package com.example.MachineProblem.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MachineProblem.model.Product;
import com.example.MachineProblem.service.ProductService;

@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getProducts(){
		return ResponseEntity.ok(productService.getAllProducts());
}

    @PostMapping("/addProducts")
	public ResponseEntity<Void> addProducts(@RequestBody Product product){
		productService.addProduct(product);
		return ResponseEntity.ok(null);
	}
    
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
    	Product product = productService.getProductId(id);
    	return ResponseEntity.ok(product);
    }
    
    @PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails){
    	productService.updateProduct(id, productDetails);

		return ResponseEntity.ok().build();
	}
	
    @GetMapping("/deleteProduct/{id}")
	public ResponseEntity<Void> deleteProducts(@PathVariable int id){
    	productService.deleteProduct(id);
    	
    	return ResponseEntity.ok(null);
    }
    
    @GetMapping("/searchProduct")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam("keyword") String keyword){
		return ResponseEntity.ok(productService.searchProduct(keyword));
	}
  
}
    
    
    
    
    
    