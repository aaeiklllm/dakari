package com.example.MachineProblem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MachineProblem.model.Customer;
import com.example.MachineProblem.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public List<Customer> getAllLogin(){
        return loginRepository.findAll();
    }

    public void addSignUp(Customer customer) {
        //customer.setUserType(customer.getUserType());
        loginRepository.save(customer);
    }

    public Optional<Customer> findCustomer(String username) {
        //loginRepository.save(customer);
        return loginRepository.findById(username);
    }

    public Optional<Customer> findCustomerByEmail(String email) {
        return loginRepository.findByEmail(email);
    }
}