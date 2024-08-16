package com.tier3Hub.quickCart.service.impl;

import com.tier3Hub.quickCart.dto.CategoryResponse;
import com.tier3Hub.quickCart.dto.CreateCategoryDto;
import com.tier3Hub.quickCart.dto.UpdateCategoryDto;
import com.tier3Hub.quickCart.entity.Category;
import com.tier3Hub.quickCart.repository.CategoryRepository;
import com.tier3Hub.quickCart.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse createCategory(CreateCategoryDto createCategoryDto) {
        Category category = modelMapper.map(createCategoryDto, Category.class);
        category.setCreatedAt(LocalDateTime.now());
        Category save = categoryRepository.save(category);
        CategoryResponse response = modelMapper.map(save, CategoryResponse.class);
        return response;
    }

    @Override
    public CategoryResponse updateCategory(UpdateCategoryDto updateCategoryDto) {
        return null;
    }

}
