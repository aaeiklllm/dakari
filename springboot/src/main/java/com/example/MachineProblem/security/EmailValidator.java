package com.example.MachineProblem.security;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

    private final String EMAIL_PATTERN = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    @Override
    public boolean test(String email) {
        return email.matches(EMAIL_PATTERN);
    }
}
