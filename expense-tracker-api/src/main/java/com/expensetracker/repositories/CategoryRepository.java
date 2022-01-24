package com.expensetracker.repositories;

import com.expensetracker.domain.Category;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll() throws ResourceNotFoundException;

    Category findById(int categoryId) throws ResourceNotFoundException;

    int create(String categoryName, boolean isIncome) throws BadRequestException;

    void update(int categoryId, Category category) throws BadRequestException;

    void removeById(int categoryId);

    void removeTransactionsForCategory(int categoryId);

}
