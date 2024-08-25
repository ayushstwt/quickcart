package com.tier3Hub.quickCart.service.impl;

import com.tier3Hub.quickCart.dto.CategoryResponse;
import com.tier3Hub.quickCart.dto.CreateCategoryDto;
import com.tier3Hub.quickCart.dto.UpdateCategoryDto;
import com.tier3Hub.quickCart.entity.Category;
import com.tier3Hub.quickCart.entity.Product;
import com.tier3Hub.quickCart.exception.ResourceNotFoundException;
import com.tier3Hub.quickCart.repository.CategoryRepository;
import com.tier3Hub.quickCart.repository.ProductRepository;
import com.tier3Hub.quickCart.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CategoryResponse createCategory(CreateCategoryDto createCategoryDto) {
        log.info("create category service called");
        Category category = modelMapper.map(createCategoryDto, Category.class);
        boolean b = categoryRepository.existsByName(createCategoryDto.getName());
        if (b)
        {
            throw new RuntimeException("Category already exists");
        }
        else {
            category.setName(createCategoryDto.getName());
        }
        category.setCreatedAt(LocalDateTime.now());
        Category save = categoryRepository.save(category);
        CategoryResponse response = modelMapper.map(save, CategoryResponse.class);
        log.info("create category service ended");
        return response;
    }

    @Override
    public CategoryResponse updateCategory(UpdateCategoryDto updateCategoryDto) {
        log.info("update category service called");
        Category category = categoryRepository.findById(updateCategoryDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        if (category.getName().equalsIgnoreCase(updateCategoryDto.getName())) {

        }
        else
        {
            boolean b = categoryRepository.existsByName(updateCategoryDto.getName());
            if (b)
            {
                throw new RuntimeException("Category already exists");
            }
            else {
                category.setName(updateCategoryDto.getName());
            }
        }
        category.setName(updateCategoryDto.getName());
        category.setUpdatedAt(LocalDateTime.now());
        category.setDescription(updateCategoryDto.getDescription());
        category.setImage(updateCategoryDto.getImage());
        category.setStatus(updateCategoryDto.isStatus());
        Category save = categoryRepository.save(category);
        CategoryResponse response = modelMapper.map(save, CategoryResponse.class);
        log.info("update category service ended");
        return response;
    }

    @Override
    public CategoryResponse getCategoryById(int id) {
        log.info("get category by id service called");
        return categoryRepository.findById(id)
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategory(int id) {
        log.info("delete category service called");
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        List<Product> products = category.getProducts();
        for (Product product : products) {
            productRepository.delete(product);
        }
        categoryRepository.delete(category);
        log.info("delete category service ended");
    }

    @Override
    public List<CategoryResponse> getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        log.info("get categories service called");
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        List<CategoryResponse> category=new ArrayList<>();
        List<Category> categories = categoryRepository.findAll(pageDetails).getContent();
        if (categories.size() == 0) {
            throw new RuntimeException("No category is created till now");
        }
        for (Category category1 : categories) {
            category.add(modelMapper.map(category1, CategoryResponse.class));
        }
        log.info("get categories service ended");
        return category;
    }

}
