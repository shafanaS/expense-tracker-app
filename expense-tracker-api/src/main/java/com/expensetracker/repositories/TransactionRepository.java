package com.expensetracker.repositories;

import com.expensetracker.domain.Transaction;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;

import java.util.List;

public interface TransactionRepository {

    int create(double amount, int categoryId, String month, Boolean isIncome, String description) throws
            BadRequestException;

    void removeById(int transactionId) throws ResourceNotFoundException;

    Transaction findById(int transactionId) throws ResourceNotFoundException;

    void update(int transactionId, Transaction transaction) throws BadRequestException;

    List<Transaction> findAll();

}
