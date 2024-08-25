package com.tier3Hub.quickCart.controller;

import com.tier3Hub.quickCart.dto.CategoryResponse;
import com.tier3Hub.quickCart.dto.CreateCategoryDto;
import com.tier3Hub.quickCart.dto.UpdateCategoryDto;
import com.tier3Hub.quickCart.service.CategoryService;
import com.tier3Hub.quickCart.utils.AppConstants;
import com.tier3Hub.quickCart.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/create")
    public ResponseEntity<Object> createCategory(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        log.info("create category controller called");
        CategoryResponse category = categoryService.createCategory(createCategoryDto);
        log.info("create category controller ended");
        return ResponseHandler.generateResponse("Category created successfully", HttpStatus.CREATED, category);
    }

    @PutMapping("/admin/update")
    public ResponseEntity<Object> updateCategory(@Valid @RequestBody UpdateCategoryDto updateCategoryDto) {
        CategoryResponse category = categoryService.updateCategory(updateCategoryDto);
        return ResponseHandler.generateResponse("Category updated successfully", HttpStatus.OK, category);
    }

    @GetMapping("/public/getCategory/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable int id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseHandler.generateResponse("Category fetched successfully", HttpStatus.FOUND, category);
    }

    @DeleteMapping("/admin/deleteCategory/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseHandler.generateResponse("Category deleted successfully", HttpStatus.OK, null);
    }

    @GetMapping("/public/categories")
    public ResponseEntity<Object> getCategories(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        List<CategoryResponse> categories = categoryService.getCategories(pageNumber, pageSize, sortBy, sortOrder);
        return ResponseHandler.generateResponse("Categories fetched successfully", HttpStatus.FOUND, categories);
    }

    @GetMapping("/api/public/test")
    public String test() {
        return "test";
    }
}
