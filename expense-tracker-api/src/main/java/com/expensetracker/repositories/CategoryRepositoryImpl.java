package com.expensetracker.repositories;

import com.expensetracker.domain.Category;
import com.expensetracker.exceptions.BadRequestException;
import com.expensetracker.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository public class CategoryRepositoryImpl implements CategoryRepository {

    public static CategoryRepositoryImpl categoryRespository;
    public static int categoryIdCounter = 0;
    private static List<Category> categoryList;


    private CategoryRepositoryImpl() {
    }

    public synchronized static CategoryRepositoryImpl getInstance() {
        if (categoryRespository == null) {
            categoryRespository = new CategoryRepositoryImpl();
            categoryList = new ArrayList<>();
            categoryList.add(new Category("Salary", false));
            categoryList.add(new Category("Travel", false));
            categoryList.add(new Category("Shopping", false));
        }
        return categoryRespository;
    }

    @Override public List<Category> findAll() throws ResourceNotFoundException {
        return categoryList;
    }

    @Override public Category findById(int categoryId) throws ResourceNotFoundException {
        Category returnCategory = null;
        for (Category category : categoryList) {
            if (category.getCategoryId() == categoryId) {
                returnCategory = category;
            }
        }
        if(returnCategory == null) {
            throw new ResourceNotFoundException("Category Not Found");
        }
        return returnCategory;
    }

    private Category findCategoryByName(String categoryName) {
        for(Category c: categoryList) {
            if(c.getCategoryName().equalsIgnoreCase(categoryName)) {
                return c;
            } else {
                continue;
            }
        }
        return null;
    }

    @Override public int create(String categoryName) throws BadRequestException {
        try {
            if(findCategoryByName(categoryName) ==null) {
                Category category = new Category(categoryName, true);
                categoryList.add(category);
                return category.getCategoryId();
            } else {
                throw new BadRequestException("Category already exists.");
            }
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override public void update(int categoryId, Category category) throws BadRequestException {
        try {
            Category existingCategory = findById(categoryId);
            existingCategory.setCategoryName(category.getCategoryName());
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override public void removeById(int categoryId) throws BadRequestException{
        if(findById(categoryId).isCustom()) {
            removeTransactionsForCategory(categoryId);
            categoryList.remove(findById(categoryId));
        } else {
            throw new BadRequestException("Category cannot be removed.");
        }
    }

    @Override public void removeTransactionsForCategory(int categoryId) {
        //TODo
    }
}
