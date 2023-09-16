package com.example.MachineProblem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@Column(name = "username")
	@JsonProperty("username")
	String username;
	
	@Column(name = "first_name")
	@JsonProperty("first_name")
	String firstName;
	
	@Column(name = "last_name")
	@JsonProperty("last_name")
	String lastName;

	@Column(name = "email")
	@JsonProperty("email")
	String email;
	
	@Column(name = "password")
	@JsonProperty("password")
	String password;
	
	@Column(name = "user_type")
	@JsonProperty("userType")
	String userType;
	
	private boolean enabled;
	
	public Customer() {
			
		}

	public Customer(String username, String firstName, String lastName, String email, String password, String userType) {
        this.username = username;
		this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.enabled = false;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
