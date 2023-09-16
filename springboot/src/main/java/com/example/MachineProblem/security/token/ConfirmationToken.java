package com.example.MachineProblem.security.token;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.example.MachineProblem.model.Customer;

@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false,
                name = "username")
    private Customer customer;

    public ConfirmationToken() {
    }

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Customer customer) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}