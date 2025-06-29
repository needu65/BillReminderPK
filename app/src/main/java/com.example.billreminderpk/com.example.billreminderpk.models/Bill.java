package com.example.billreminderpk.models;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {
    private String id;
    private String billType; // electricity, gas, water, etc.
    private String company;
    private String accountNumber;
    private Date dueDate;
    private double amount;
    private boolean isPaid;
    private String userId;

    // Constructor, getters and setters
    public Bill(String billType, String company, String accountNumber, Date dueDate, double amount, String userId) {
        this.billType = billType;
        this.company = company;
        this.accountNumber = accountNumber;
        this.dueDate = dueDate;
        this.amount = amount;
        this.userId = userId;
        this.id = java.util.UUID.randomUUID().toString();
        this.isPaid = false;
    }

    // Getters and setters for all fields
    public String getId() { return id; }
    public String getBillType() { return billType; }
    public String getCompany() { return company; }
    public String getAccountNumber() { return accountNumber; }
    public Date getDueDate() { return dueDate; }
    public double getAmount() { return amount; }
    public boolean isPaid() { return isPaid; }
    public String getUserId() { return userId; }
    
    public void setPaid(boolean paid) { isPaid = paid; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
}