package com.example.MachineProblem.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receiptId;

    @Column(name = "username")
    @JsonProperty("username")
    String username;

    @Column(name = "details")
    @JsonProperty("details")
    String details;
    
    @Column(name = "timestamp")
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    
    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
