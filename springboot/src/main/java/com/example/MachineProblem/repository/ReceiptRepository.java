package com.example.MachineProblem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.MachineProblem.model.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
  
}
