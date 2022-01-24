package com.expensetracker.resources;

import com.expensetracker.domain.Transaction;
import com.expensetracker.repositories.TransactionRepositoryImpl;
import com.expensetracker.services.TransactionService;
import com.expensetracker.services.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController() {
        this.transactionService = new TransactionServiceImpl(TransactionRepositoryImpl.getInstance());
    }

    @GetMapping("/getTransactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.fetchAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/newTransaction")
    public ResponseEntity<Integer> addTransaction(@RequestBody Transaction transaction) {
        int transactionId = transactionService.addTransaction(transaction);
        return new ResponseEntity<Integer>(transactionId, HttpStatus.CREATED);
    }

    @PostMapping("/updateTransaction/{transactionId}")
    public ResponseEntity<?> updateTransaction(@RequestBody Transaction transaction, @PathVariable("transactionId") int transactionId) {
        transactionService.updateTransaction(transactionId, transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("transactionId") int transactionId) {
        transactionService.removeTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
