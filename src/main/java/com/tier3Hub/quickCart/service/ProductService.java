package com.tier3Hub.quickCart.service;

import com.tier3Hub.quickCart.dto.CreateProductDto;
import com.tier3Hub.quickCart.dto.ProductResponse;
import com.tier3Hub.quickCart.dto.UpdateProductDto;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(CreateProductDto createProductDto);
    ProductResponse updateProduct(UpdateProductDto updateProductDto);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    List<ProductResponse> getAllProductsByCategory(int categoryId,Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    List<ProductResponse> getAllProductsBySearching(String keyword,Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    void deleteProductById(Long id);
}
