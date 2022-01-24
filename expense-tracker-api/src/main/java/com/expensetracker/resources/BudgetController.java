package com.expensetracker.resources;

import com.expensetracker.domain.Budget;
import com.expensetracker.domain.MonthlyBudget;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.repositories.BudgetRepositoryImpl;
import com.expensetracker.services.BudgetService;
import com.expensetracker.services.BudgetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BudgetController {

    private BudgetService budgetService;

    @Autowired
    public BudgetController() {
        this.budgetService = new BudgetServiceImpl(BudgetRepositoryImpl.getInstance());
    }

    @GetMapping("/getBudgets")
    public ResponseEntity<List<MonthlyBudget>> getAllBudgets() {
        List<MonthlyBudget> monthlyBudgets = budgetService.fetchBudgets();
        return new ResponseEntity<>(monthlyBudgets, HttpStatus.OK);
    }

    @PostMapping("/newBudget")
    public ResponseEntity<?> addBudget(@RequestBody Budget budget) {
        try {
            Budget newBudget = budgetService.addBudget(budget);
            return new ResponseEntity<>(newBudget, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
