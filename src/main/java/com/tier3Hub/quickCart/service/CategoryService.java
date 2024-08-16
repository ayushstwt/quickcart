package com.tier3Hub.quickCart.service;

import com.tier3Hub.quickCart.dto.CategoryResponse;
import com.tier3Hub.quickCart.dto.CreateCategoryDto;
import com.tier3Hub.quickCart.dto.UpdateCategoryDto;

public interface CategoryService {
    CategoryResponse createCategory(CreateCategoryDto createCategoryDto);
    CategoryResponse updateCategory(UpdateCategoryDto updateCategoryDto);

}
