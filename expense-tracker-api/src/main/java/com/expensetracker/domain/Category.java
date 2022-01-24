package com.expensetracker.domain;

import com.expensetracker.repositories.CategoryRepositoryImpl;
import com.expensetracker.util.Util;

public class Category {

    private int categoryId;
    private String categoryName;
    private boolean isCustom;

    public Category(String categoryName, boolean isCustom ) {
        this.categoryId = Util.createID(CategoryRepositoryImpl.categoryIdCounter);
        this.categoryName = categoryName;
        this.isCustom = isCustom;
        CategoryRepositoryImpl.categoryIdCounter = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }
}