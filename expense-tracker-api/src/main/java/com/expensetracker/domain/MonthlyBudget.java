package com.expensetracker.domain;

import java.util.List;

public class MonthlyBudget {
    private String month;
    private double totalIncome;
    private double totalExpense;
    private double totalBudget;
    private List<CategoryBudget> categorylist;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public List<CategoryBudget> getCategorylist() {
        return categorylist;
    }

    public void setCategorylist(List<CategoryBudget> categorylist) {
        this.categorylist = categorylist;
    }
}
