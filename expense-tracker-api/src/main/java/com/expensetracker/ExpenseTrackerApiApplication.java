package com.expensetracker;

import com.expensetracker.repositories.CategoryRepository;
import com.expensetracker.repositories.CategoryRepositoryImpl;
import com.expensetracker.repositories.TransactionRepository;
import com.expensetracker.repositories.TransactionRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApiApplication.class, args);
	}
}
