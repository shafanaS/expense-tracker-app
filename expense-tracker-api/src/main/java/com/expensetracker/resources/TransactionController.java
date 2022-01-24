package com.expensetracker.resources;

import com.expensetracker.domain.Transaction;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction) {
        try {
            int transactionId = transactionService.addTransaction(transaction);
            return new ResponseEntity<>(transactionId, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateTransaction/{transactionId}")
    public ResponseEntity<?> updateTransaction(@RequestBody Transaction transaction, @PathVariable("transactionId") int transactionId) {
        try {
            transactionService.updateTransaction(transactionId, transaction);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteTransaction/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("transactionId") int transactionId) {
        try {
            transactionService.removeTransaction(transactionId);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
