package com.expensetracker.services;

import com.expensetracker.domain.Transaction;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;

import java.util.List;

public interface TransactionService {

    int addTransaction(Transaction transaction) throws
            BadRequestException;

    void removeTransaction(int transactionId) throws ResourceNotFoundException;

    List<Transaction> fetchAllTransactions();

    Transaction findTransaction(int transactionId) throws ResourceNotFoundException;

    void updateTransaction(int transactionId, Transaction transaction) throws ResourceNotFoundException;
}
