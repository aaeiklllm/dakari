package com.example.MachineProblem.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.MachineProblem.service.CustomerService;
import com.example.MachineProblem.service.LoginService;
import com.example.MachineProblem.service.RegistrationService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MachineProblem.email.EmailSender;
import com.example.MachineProblem.model.Customer;
import com.example.MachineProblem.model.RegistrationRequest;
import com.example.MachineProblem.security.EmailValidator;
import com.example.MachineProblem.security.token.ConfirmationToken;
import com.example.MachineProblem.security.token.ConfirmationTokenService;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    
    @Autowired
    CustomerService customerService;
    
    @Autowired
    EmailValidator emailValidator;
    
    @Autowired
    RegistrationService registrationService;
    
    @Autowired
    ConfirmationTokenService confirmTokenService;
    
    @Autowired
    EmailSender emailSender;

    @GetMapping("/getLogin")
    public ResponseEntity<List<Customer>> getLogin(){
        return ResponseEntity.ok(loginService.getAllLogin());
    }
    
    @PostMapping("/addSignUp")
    public String register(@RequestBody RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (isValidEmail) {
            String tokenForNewUser = customerService.signUpUser(new Customer(
            		request.getUsername(),
            		request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getUserType()));

            //Since, we are running the spring boot application in localhost, we are hardcoding the
            //url of the server. We are creating a POST request with token param
            String link = "http://localhost:8080/confirm?token=" + tokenForNewUser;
            emailSender.sendEmail(request.getEmail(), buildEmail(request.getFirstName(), link, tokenForNewUser));
            return tokenForNewUser;
        } else {
            throw new IllegalStateException(String.format("Email %s, not valid", request.getEmail()));
        }
    }

    @PostMapping("/addLogin")
    public ResponseEntity<String> addLogin(@RequestBody Customer details) {
        String username = details.getUsername();
        String password = details.getPassword();

        Optional<Customer> existingCustomer = loginService.findCustomer(username);

        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            
            if (!customer.getEnabled()) {
            	return ResponseEntity.badRequest().body("Not verified");
            }
            	
            else {
            	if (customer.getPassword().equals(password)) {
                // User is authenticated
                         //return ResponseEntity.ok().body("Login successful");
            		String userType = customer.getUserType(); // Retrieve the userType from the Customer object
                    return ResponseEntity.ok(userType); // Return the userType in the response
                } 
            	else {
                         // Incorrect password
            		return ResponseEntity.badRequest().body("Incorrect password");
            	}
          } 
        } else {
            // User does not exist
            return ResponseEntity.badRequest().body("User not found");
        }
 
        
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
    
    private String buildEmail(String name, String link, String token) {
        return "<div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-size: 16px; color: #333333; background-color: #f9f9f9; padding: 20px;\">" +
        		  "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; padding: 20px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\">" +
        		    "<h2 style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-weight: bold; color: #333333; font-size: 24px; margin-bottom: 20px;\">Confirm your email</h2>" +
        		    "<p style=\"margin-bottom: 20px;\">Hi <strong>" + name + "</strong>,</p>" +
        		    "<p style=\"margin-bottom: 20px;\">Thank you for registering. Please click the button below to activate your account:</p>" +
        		    "<div style=\"text-align: center; margin-bottom: 20px;\">" +
        		      "<a href=\"" + link + "\"style=\"background-color: #1D70B8; color: #ffffff; text-decoration: none; padding: 12px 24px; border-radius: 4px; font-weight: bold;\">Activate Now</a>" +
        		    "</div>" +
        		    "<blockquote style=\"border-left: 2px solid #b1b4b6; padding-left: 10px; margin-bottom: 20px;\">" +
        		      "<p style=\"margin-bottom: 20px;\">Verification code: " + token +
        		    "</blockquote>" +
        		    "<p style=\"margin-bottom: 0;\">The link will expire in 15 minutes.</p>" +
        		    "<p style=\"margin-bottom: 0;\">See you soon!</p>" +
        		  "</div>" +
        		"</div>";

    }

}