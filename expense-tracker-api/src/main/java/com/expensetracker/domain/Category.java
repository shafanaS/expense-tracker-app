package com.expensetracker.domain;

import com.expensetracker.repositories.CategoryRepositoryImpl;
import com.expensetracker.util.Util;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {

    private int categoryId;
    private String categoryName;
    @JsonProperty("isCustom")
    private boolean isCustom;
    @JsonProperty("isIncome")
    private boolean isIncome;

    public Category(String categoryName, boolean isCustom, boolean isIncome ) {
        this.categoryId = Util.createID(CategoryRepositoryImpl.categoryIdCounter);
        this.categoryName = categoryName;
        this.isCustom = isCustom;
        this.isIncome = isIncome;
        CategoryRepositoryImpl.categoryIdCounter = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
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

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }
}