package com.example.MachineProblem.model;

public class RegistrationRequest {

	private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String userType;

    public RegistrationRequest(String username, String firstName, String lastName, String email, String password, String userType) {
        this.username = username;
    	this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    
    public String getUsername() {
    	return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getUserType() {
        return userType;
    }
}
