package com.tier3Hub.quickCart.controller;

import com.tier3Hub.quickCart.dto.CategoryResponse;
import com.tier3Hub.quickCart.dto.CreateCategoryDto;
import com.tier3Hub.quickCart.service.CategoryService;
import com.tier3Hub.quickCart.utils.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/create")
    public ResponseEntity<Object> createCategory(@Valid CreateCategoryDto createCategoryDto) {
        CategoryResponse category = categoryService.createCategory(createCategoryDto);
        return ResponseHandler.generateResponse("Category created successfully", HttpStatus.CREATED, category);
    }

}
