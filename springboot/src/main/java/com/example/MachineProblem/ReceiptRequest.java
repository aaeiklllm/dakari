package com.example.MachineProblem;

import java.util.List;

public class ReceiptRequest {
    private String username;
    private List<ReceiptDetail> details;

    // Getters and setters

    // Inner class representing a receipt detail
    public static class ReceiptDetail {
        private String productName;
        private int quantity;

        // Getters and setters
    }
}