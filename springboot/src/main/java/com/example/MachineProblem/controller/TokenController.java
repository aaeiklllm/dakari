package com.example.MachineProblem.controller;

import com.example.MachineProblem.security.token.ConfirmationToken;
import com.example.MachineProblem.security.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    private final ConfirmationTokenService tokenService;

    @Autowired
    public TokenController(ConfirmationTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/{token}")
    public ResponseEntity<String> getToken(@PathVariable("token") String token) {
        Optional<ConfirmationToken> optionalToken = tokenService.getToken(token);

        if (optionalToken.isPresent()) {
            ConfirmationToken foundToken = optionalToken.get();
            String tokenValue = foundToken.getToken();
            return ResponseEntity.ok(tokenValue);
        } else {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().body("Not verified");
        }
    }

}