package com.tier3Hub.quickCart.controller;

import com.tier3Hub.quickCart.dto.CreateProductDto;
import com.tier3Hub.quickCart.dto.ProductResponse;
import com.tier3Hub.quickCart.dto.UpdateProductDto;
import com.tier3Hub.quickCart.service.ProductService;
import com.tier3Hub.quickCart.utils.AppConstants;
import com.tier3Hub.quickCart.utils.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/products")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        ProductResponse product = productService.addProduct(createProductDto);
        return ResponseHandler.generateResponse("Product created successfully", HttpStatus.CREATED, product);
    }

    @PutMapping("/admin/update")
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody UpdateProductDto updateProductDto) {
        ProductResponse product = productService.updateProduct(updateProductDto);
        return ResponseHandler.generateResponse("Product updated successfully", HttpStatus.OK, product);
    }

    @GetMapping("/admin/products/{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable Long productId) {
        ProductResponse product = productService.getProductById(productId);
        return ResponseHandler.generateResponse("Product fetched successfully", HttpStatus.FOUND, product);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseHandler.generateResponse("Product deleted successfully", HttpStatus.OK, null);
    }

    @GetMapping("/public/products")
    public ResponseEntity<Object> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        return ResponseHandler.generateResponse("Products fetched successfully", HttpStatus.FOUND, productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder));
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<Object> getProductByName(
            @PathVariable String keyword,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder

    ) {
        List<ProductResponse> product = productService.getAllProductsBySearching(keyword, pageNumber, pageSize, sortBy, sortOrder);
        return ResponseHandler.generateResponse("Product fetched successfully", HttpStatus.FOUND, product);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<Object> getAllProductsByCategory(
            @PathVariable int categoryId,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        List<ProductResponse> products = productService.getAllProductsByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return ResponseHandler.generateResponse("Products fetched successfully", HttpStatus.FOUND,products);
    }

}
