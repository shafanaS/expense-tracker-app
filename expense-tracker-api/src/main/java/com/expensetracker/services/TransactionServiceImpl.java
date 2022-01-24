package com.expensetracker.services;

import com.expensetracker.domain.Transaction;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;
import com.expensetracker.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl (TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public int addTransaction(Transaction transaction) throws BadRequestException {
        return transactionRepository.create(transaction.getAmount(), transaction.getCategoryId(), transaction.getMonth(), transaction.isIncome(), transaction.getDescription());
    }

    @Override
    public void updateTransaction(int transactionId, Transaction transaction) throws
            BadRequestException {
        transactionRepository.update(transactionId, transaction);
    }

    @Override
    public void removeTransaction(int transactionId) throws
            ResourceNotFoundException {
        transactionRepository.removeById(transactionId);
    }

    @Override public List<Transaction> fetchAllTransactions() {
       return transactionRepository.findAll();
    }

    @Override public Transaction findTransaction(int transactionId) throws ResourceNotFoundException {
        return transactionRepository.findById(transactionId);
    }
}