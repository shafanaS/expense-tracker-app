package com.expensetracker.domain;

import com.expensetracker.repositories.TransactionRepositoryImpl;
import com.expensetracker.util.Util;

public class Transaction {

    private int transactionId;
    private double amount;
    private int categoryId;
    private String month;
    private boolean isIncome;
    private String description;

    public Transaction(double amount, int categoryId, String month, boolean isIncome, String description) {
        transactionId = Util.createID(TransactionRepositoryImpl.transactionIdCounter);
        this.amount = amount;
        this.categoryId = categoryId;
        this.month=month;
        this.isIncome = isIncome;
        this.description = description;
        TransactionRepositoryImpl.transactionIdCounter = transactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
