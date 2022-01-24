package com.expensetracker.services;

import com.expensetracker.domain.Category;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;
import com.expensetracker.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl (CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override public List<Category> fetchAllCategories() {
        return categoryRepository.findAll();
    }

    @Override public Category fetchCategoryById(int categoryId) throws ResourceNotFoundException {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public int addCategory(String categoryName) throws BadRequestException {
        return  categoryRepository.create(categoryName);
    }

    @Override public void updateCategory(int categoryId, Category category) throws BadRequestException {
        categoryRepository.update(categoryId, category);
    }

    @Override public void removeCategory(int categoryId) throws ResourceNotFoundException {
        categoryRepository.removeById(categoryId);
    }
}
