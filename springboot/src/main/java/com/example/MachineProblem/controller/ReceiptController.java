package com.example.MachineProblem.controller;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.example.MachineProblem.model.CartItem;
import com.example.MachineProblem.model.Product;
import com.example.MachineProblem.model.Receipt;
import com.example.MachineProblem.repository.ReceiptRepository;
import com.example.MachineProblem.service.ReceiptService;

@CrossOrigin
@RestController
public class ReceiptController {
	@Autowired
    ReceiptService receiptService;
	
	@Autowired
	ReceiptRepository receiptRepository;


    @PostMapping("/receipt")
    public ResponseEntity<Integer> generateReceipt(@RequestParam("customerId") String customerId, @RequestBody String details) {
            Receipt receipt = new Receipt();
            receipt.setUsername(customerId);
            receipt.setDetails(details);
            receipt.setTimestamp(LocalDateTime.now());
			
			Receipt savedReceipt = receiptRepository.save(receipt);
		    int receiptId = savedReceipt.getReceiptId();
			
		    return ResponseEntity.ok(receiptId);  
    }
    
    @GetMapping("/getReceipt/{id}")
   	public ResponseEntity<String> getReceipt(@PathVariable int id){
    	Receipt receipt = receiptService.getReceiptById(id);
   		return ResponseEntity.ok(receipt.getDetails());
   }
    
    @GetMapping("/getReceipts")
	public ResponseEntity<List<Receipt>> getReceipts(){
		return ResponseEntity.ok(receiptService.getAllReceipts());
}


}