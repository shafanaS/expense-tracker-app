package com.expensetracker.services;

import com.expensetracker.domain.Category;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> fetchAllCategories();

    Category fetchCategoryById(int categoryId) throws ResourceNotFoundException;

    int addCategory(String categoryName, boolean isIncome) throws BadRequestException;

    void updateCategory(int categoryId, Category category) throws BadRequestException;

    void removeCategory(int categoryId) throws ResourceNotFoundException;
}
