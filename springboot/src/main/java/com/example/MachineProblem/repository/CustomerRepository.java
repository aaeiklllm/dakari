package com.example.MachineProblem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.MachineProblem.model.Customer;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CustomerRepository extends JpaRepository<Customer, String>{
	Optional<Customer> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.enabled=true WHERE c.email=?1")
    int enableAppUser(String email);
}