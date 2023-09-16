package com.example.MachineProblem.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.example.demo.springboot.model.Student;

import com.example.MachineProblem.model.Customer;

@Repository
public interface LoginRepository extends JpaRepository<Customer, String>{


    // basic function provided by springdata jpa itohhh
    Optional<Customer> findByEmail(String email);

}