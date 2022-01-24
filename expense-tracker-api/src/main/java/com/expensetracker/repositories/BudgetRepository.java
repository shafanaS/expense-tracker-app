package com.expensetracker.repositories;

import com.expensetracker.domain.Budget;
import com.expensetracker.domain.MonthlyBudget;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;

import java.util.List;

public interface BudgetRepository {
    List<Budget> findAll();

    List<MonthlyBudget> findAllMonthlyBudget();

    Budget create(double budget, int categoryId, String month) throws BadRequestException;
}
