package com.tier3Hub.quickCart.service.impl;

import com.tier3Hub.quickCart.repository.ProductRepository;
import com.tier3Hub.quickCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
}
