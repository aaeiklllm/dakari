package com.example.MachineProblem.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.MachineProblem.email.EmailSender;
import com.example.MachineProblem.model.Customer;
import com.example.MachineProblem.model.RegistrationRequest;
import com.example.MachineProblem.security.EmailValidator;
import com.example.MachineProblem.security.token.ConfirmationToken;
import com.example.MachineProblem.security.token.ConfirmationTokenService;

import jakarta.transaction.Transactional;

@Service
public class RegistrationService {

    private final CustomerService customerService;
    private final ConfirmationTokenService confirmTokenService;


    public RegistrationService(CustomerService customerService, ConfirmationTokenService confirmTokenService) {
        this.customerService = customerService;
        this.confirmTokenService = confirmTokenService;
    }

//    public String register(RegistrationRequest request) {
//        boolean isValidEmail = emailValidator.test(request.getEmail());
//        if (isValidEmail) {
//            String tokenForNewUser = customerService.signUpUser(new Customer(request.getFirstName(),
//                    request.getLastName(),
//                    request.getEmail(),
//                    request.getPassword()));
//
//            //Since, we are running the spring boot application in localhost, we are hardcoding the
//            //url of the server. We are creating a POST request with token param
//            String link = "http://localhost:8080/sconfirm?token=" + tokenForNewUser;
//            emailSender.sendEmail(request.getEmail(), buildEmail(request.getFirstName(), link));
//            return tokenForNewUser;
//        } else {
//            throw new IllegalStateException(String.format("Email %s, not valid", request.getEmail()));
//        }
//    }

    @Transactional
    public String confirmToken(String token) {
        Optional<ConfirmationToken> confirmToken = confirmTokenService.getToken(token);

        if (confirmToken.isEmpty()) {
            throw new IllegalStateException("Token not found!");
        }

        if (confirmToken.get().getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed");
        }

        LocalDateTime expiresAt = confirmToken.get().getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is already expired!");
        }

        confirmTokenService.setConfirmedAt(token);
        customerService.enableAppUser(confirmToken.get().getCustomer().getEmail());

        //Returning confirmation message if the token matches
        return "Your email is confirmed. Thank you for using our service!";
    }

}