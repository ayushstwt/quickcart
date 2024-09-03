package com.tier3Hub.quickCart.service.impl;

import com.tier3Hub.quickCart.dto.CreateProductDto;
import com.tier3Hub.quickCart.dto.ProductResponse;
import com.tier3Hub.quickCart.dto.UpdateProductDto;
import com.tier3Hub.quickCart.entity.Category;
import com.tier3Hub.quickCart.entity.Product;
import com.tier3Hub.quickCart.repository.CategoryRepository;
import com.tier3Hub.quickCart.repository.ProductRepository;
import com.tier3Hub.quickCart.service.ProductService;
import org.modelmapper.ModelMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductResponse addProduct(CreateProductDto createProductDto) {
        Category category = categoryRepository.findById(createProductDto.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = modelMapper.map(createProductDto, Product.class);
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.now());
        Product save = productRepository.save(product);
        ProductResponse response = modelMapper.map(save, ProductResponse.class);
        return response;
    }

    @Override
    public ProductResponse updateProduct(UpdateProductDto updateProductDto) {
        Category category = categoryRepository.findById(updateProductDto.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product=productRepository.findById(updateProductDto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setUpdatedAt(LocalDateTime.now());
        product.setCategory(category);
        product.setImage(updateProductDto.getImage());
        product.setName(updateProductDto.getName());
        product.setPrice(updateProductDto.getPrice());
        product.setQuantity(updateProductDto.getQuantity());
        product.setSpecialPrice(updateProductDto.getSpecialPrice());
        product.setDescription(updateProductDto.getDescription());
        Product save = productRepository.save(product);
        ProductResponse response = modelMapper.map(save, ProductResponse.class);
        return response;
    }


    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Product> products = productRepository.findAll(pageDetails);
        if (products.isEmpty()) {
            throw new RuntimeException("No product is created till now");
        }
        return products.stream()
                    .map(product -> modelMapper.map(product, ProductResponse.class))
                    .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProductsByCategory(int categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Product> products = productRepository.findAll(pageDetails);
        if (products.isEmpty()) {
            throw new RuntimeException("No product is created till now");
        }
        List<Product> productList = products.getContent();

        for (Product product : productList) {
            if (product.getCategory().getId() == categoryId) {
                productList.add(product);
            }
        }
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProductsBySearching(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Product> products = productRepository.findByNameLike(keyword,pageDetails);
        if (products.isEmpty()) {
            throw new RuntimeException("No product is created till now");
        }

        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(Long id) {
        Product product=productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }


}
