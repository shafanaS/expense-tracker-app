package com.expensetracker.services;

import com.expensetracker.domain.Budget;
import com.expensetracker.domain.MonthlyBudget;
import com.expensetracker.exceptions.BadRequestException;

import java.util.List;

public interface BudgetService {

    List<MonthlyBudget> fetchBudgets();

    Budget addBudget(Budget budget) throws BadRequestException;
}
