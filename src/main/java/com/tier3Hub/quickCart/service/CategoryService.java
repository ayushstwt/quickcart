package com.tier3Hub.quickCart.service;

import com.tier3Hub.quickCart.dto.CategoryResponse;
import com.tier3Hub.quickCart.dto.CreateCategoryDto;
import com.tier3Hub.quickCart.dto.UpdateCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CreateCategoryDto createCategoryDto);
    CategoryResponse updateCategory(UpdateCategoryDto updateCategoryDto);
    CategoryResponse getCategoryById(int id);
    void deleteCategory(int id);
    List<CategoryResponse> getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
