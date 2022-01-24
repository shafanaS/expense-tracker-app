package com.expensetracker.domain;

public class Budget {

    private String month;
    private int categoryId;
    private double budget = 0;

    public Budget(String month, int categoryId, double budget) {
        this.month = month;
        this.categoryId = categoryId;
        this.budget = budget;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
