package com.expensetracker.resources;

import com.expensetracker.domain.Category;
import com.expensetracker.exceptions.ResourceNotFoundException;
import com.expensetracker.repositories.CategoryRepositoryImpl;
import com.expensetracker.services.CategoryService;
import com.expensetracker.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController() {
        this.categoryService = new CategoryServiceImpl(CategoryRepositoryImpl.getInstance());
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.fetchAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/newCategory")
    public ResponseEntity<Integer> addCategory(@RequestBody Category category) {
        int categoryId = categoryService.addCategory(category.getCategoryName());
        return new ResponseEntity<Integer>(categoryId, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") int categoryId) {
        try {
            categoryService.removeCategory(categoryId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

