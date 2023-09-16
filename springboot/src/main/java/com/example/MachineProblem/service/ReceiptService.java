package com.example.MachineProblem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MachineProblem.model.Product;
import com.example.MachineProblem.model.Receipt;
import com.example.MachineProblem.repository.ReceiptRepository;

@Service
public class ReceiptService {
	@Autowired
    ReceiptRepository receiptRepository;

	public Receipt getReceiptById(int id){
		return receiptRepository.findById(id).get();
	}
	
	public List<Receipt> getAllReceipts(){
		return receiptRepository.findAll();
	}
}