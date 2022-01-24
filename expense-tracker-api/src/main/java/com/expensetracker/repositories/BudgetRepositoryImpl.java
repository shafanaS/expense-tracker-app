package com.expensetracker.repositories;

import com.expensetracker.domain.*;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.util.Month;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BudgetRepositoryImpl implements BudgetRepository {

    public static BudgetRepositoryImpl budgetRespository;
    public static int budgetIdCounter = 0;
    private static List<Budget> budgetList;


    private BudgetRepositoryImpl() {
    }

    public synchronized static BudgetRepositoryImpl getInstance() {
        if (budgetRespository == null) {
            budgetRespository = new BudgetRepositoryImpl();
            budgetList = new ArrayList<>();
        }
        return budgetRespository;
    }

    @Override public List<Budget> findAll() {
        return budgetList;
    }

    @Override public List<MonthlyBudget> findAllMonthlyBudget() {
        List<MonthlyBudget> monthlyBudgetList = new ArrayList<>();
        List<Transaction> transactionList = TransactionRepositoryImpl.transactionRepository.findAll();
        List<Category> categoryList = CategoryRepositoryImpl.categoryRespository.findAll();
        List<Budget> budgetList = BudgetRepositoryImpl.budgetRespository.findAll();

        for(Month month: Month.values()) {
            List<Transaction> monthlyTransactions = filterTransactionsByMonth(transactionList, month.toString());
            MonthlyBudget monthlyBudget = getMonthlyBudgetInfo(monthlyTransactions, categoryList, month.toString(), budgetList);
            monthlyBudgetList.add(monthlyBudget);
        }
        return monthlyBudgetList;
    }

    @Override public Budget create(double budget, int categoryId, String month) throws BadRequestException {
        Budget newBudget = new Budget(month, categoryId, budget);
        budgetList.add(newBudget);
        return newBudget;
    }

    private MonthlyBudget getMonthlyBudgetInfo(List<Transaction> transactionForMonth, List<Category> categoryList, String month, List<Budget> budgetList) {

        List<CategoryBudget> categoryBudgets = findCategoryBudgetsForCategory(categoryList, transactionForMonth, month, budgetList);
        MonthlyBudget monthlyBudget = new MonthlyBudget();
        monthlyBudget.setMonth(month);
        monthlyBudget.setTotalBudget(findTotalBudget(month));
        monthlyBudget.setTotalIncome(totalIncomeByMonth(transactionForMonth));
        monthlyBudget.setTotalExpense(totalExpenseByMonth(transactionForMonth));
        monthlyBudget.setCategorylist(categoryBudgets);
        return monthlyBudget;
    }

    private List<CategoryBudget> findCategoryBudgetsForCategory(List<Category> categoryList, List<Transaction> monthlyTransactions, String month, List<Budget> budgetList) {
        List<CategoryBudget> returnList = new ArrayList<>();
        for(Category category: categoryList) {
            CategoryBudget categoryBudget = findCategoryBudgetForMonth(monthlyTransactions, category.getCategoryId(),budgetList, month);
            returnList.add(categoryBudget);
        }
        return returnList;
    }

    private CategoryBudget findCategoryBudgetForMonth(List<Transaction> transactionForMonth, int categoryId, List<Budget> budgetList, String month) {

        double totalSpent = findExpenseForCategory(transactionForMonth,categoryId);
        String name = CategoryRepositoryImpl.categoryRespository.findById(categoryId).getCategoryName();
        double budget = findBudgetForCategoryForMonth(categoryId, budgetList, month);
        CategoryBudget categoryBudget = new CategoryBudget();
        categoryBudget.setCategoryId(categoryId);
        categoryBudget.setSpent(totalSpent);
        categoryBudget.setName(name);
        categoryBudget.setBudget(budget);
        return categoryBudget;
    }

    private double findBudgetForCategoryForMonth(int categoryId, List<Budget> budgetList, String month) {
        for(Budget budget: budgetList) {
            if(budget.getCategoryId() == categoryId && budget.getMonth().equalsIgnoreCase(month)) {
                return budget.getBudget();
            }
        }
        return 0;
    }

    private double findExpenseForCategory(List<Transaction> transactionForMonth, int categoryId) {
        double totalSpent = 0;
        for (Transaction transaction : transactionForMonth) {
            if (transaction.getCategoryId() == categoryId) {
                totalSpent = totalSpent + transaction.getAmount();
            }
        }
        return totalSpent;
    }

    private double calculateTotal(double amount, Transaction transaction) {
        return amount + transaction.getAmount();
    }

    private List<Transaction> filterTransactionsByMonth(List<Transaction> transactionList, String month) {
        List<Transaction> returnList = new ArrayList<>();
        for(Transaction transaction: transactionList) {
            if(transaction.getMonth().equalsIgnoreCase(month)) {
                returnList.add(transaction);
            }
        }
        return returnList;
    }

    private double totalExpenseByMonth(List<Transaction> transactions) {
        double totalExpense = 0;
        for(Transaction transaction: transactions) {
            if(!transaction.isIncome()) {
                totalExpense = calculateTotal(totalExpense, transaction);
            }
        }
        return totalExpense;
    }

    private double totalIncomeByMonth(List<Transaction> transactions) {
        double totalIncome = 0;
        for(Transaction transaction: transactions) {
            if(transaction.isIncome()) {
                totalIncome = calculateTotal(totalIncome, transaction);
            }
        }
        return totalIncome;
    }

    private double findTotalBudget(String month) {
        double amount = 0;
        for(Budget budget: BudgetRepositoryImpl.budgetRespository.findAll()) {
            if (budget.getMonth().equalsIgnoreCase(month)) {
                amount = amount + budget.getBudget();
            }
        }
        return amount;
    }
}
