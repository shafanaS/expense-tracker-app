package com.expensetracker.domain;

public class IncomeTransaction implements TransactionType {

    private double amount;

    @Override public double calculateBalance(double amount) {
        return this.amount + amount;
    }
}
