package com.example.MachineProblem.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MachineProblem.model.Customer;
import com.example.MachineProblem.repository.CustomerRepository;
import com.example.MachineProblem.security.PasswordEncoder;
import com.example.MachineProblem.security.token.ConfirmationTokenService;
import com.example.MachineProblem.security.token.ConfirmationToken;

@Service
public class CustomerService implements UserDetailsService {
	@Autowired
	CustomerRepository customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepo, PasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }
	
	public Customer getCustomerId(String id){
		return customerRepo.findById(id).get();
	}
	
	public String signUpUser(Customer customer) {
        boolean userExists = customerRepo.findByEmail(customer.getEmail()).isPresent();

        if (userExists) {

            Customer customerPrevious =  customerRepo.findByEmail(customer.getEmail()).get();
            Boolean isEnabled = customerPrevious.getEnabled();

            if (!isEnabled) {
                String token = UUID.randomUUID().toString();

                //A method to save user and token in this class
                saveConfirmationToken(customerPrevious, token);

                return token;

            }
            throw new IllegalStateException(String.format("User with email %s already exists!", customer.getEmail()));
        }
        customer.setPassword(customer.getPassword());

        //Saving the user after encoding the password
        customerRepo.save(customer);

        //Creating a token from UUID
        String token = UUID.randomUUID().toString();

        //Getting the confirmation token and then saving it
        saveConfirmationToken(customer, token);


        //Returning token
        return token;
    }
        
        
        private void saveConfirmationToken(Customer customer, String token) {
            ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15), customer);
            confirmationTokenService.saveConfirmationToken(confirmationToken);
        }

        public int enableAppUser(String email) {
            return customerRepo.enableAppUser(email);

        }

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return (UserDetails) customerRepo.findByEmail(email)
                                    .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
        }

}
