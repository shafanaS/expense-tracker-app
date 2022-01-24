package com.expensetracker.repositories;

import com.expensetracker.domain.Category;
import com.expensetracker.domain.Transaction;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    public static TransactionRepositoryImpl transactionRepository;
    public static int transactionIdCounter = 0;
    private static List<Transaction> transactionList;

    private void TransactionRepositoryImpl() {
    }

    public synchronized static TransactionRepositoryImpl getInstance() {
        if(transactionRepository == null) {
            transactionRepository = new TransactionRepositoryImpl();
            transactionList = new ArrayList<>();
        }
        return transactionRepository;
    }

    @Override
    public int create(double amount, int categoryId, String month, Boolean isIncome, String description) throws BadRequestException {
        try {
            Transaction transaction = new Transaction(amount, categoryId, month, isIncome, description);
            transactionList.add(transaction);
            return transaction.getTransactionId();
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override public void removeById(int transactionId) throws ResourceNotFoundException {
        transactionList.remove(findById(transactionId));
    }

    @Override public Transaction findById(int transactionId) throws ResourceNotFoundException {
        for(Transaction transaction: transactionList) {
            if(transaction.getTransactionId() == transactionId) {
                return transaction;
            } else {
                throw new ResourceNotFoundException("Transaction not found");
            }
        }
        return null;
    }

    @Override
    public void update(int transactionId, Transaction transaction) throws BadRequestException {
        try {
            Transaction transaction1 = findById(transactionId);
            transaction1.setAmount(transaction.getAmount());
            transaction1.setDescription(transaction.getDescription());
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override public List<Transaction> findAll() {
        return transactionList;
    }
}