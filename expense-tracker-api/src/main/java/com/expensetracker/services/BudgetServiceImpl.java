package com.expensetracker.services;

import com.expensetracker.domain.Budget;
import com.expensetracker.domain.MonthlyBudget;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.repositories.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService{

    private BudgetRepository budgetRepository;

    public BudgetServiceImpl (BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override public List<MonthlyBudget> fetchBudgets() {
        return budgetRepository.findAllMonthlyBudget();
    }

    @Override public Budget addBudget(Budget budget) throws BadRequestException {
        return budgetRepository.create(budget.getBudget(), budget.getCategoryId(), budget.getMonth());
    }
}
