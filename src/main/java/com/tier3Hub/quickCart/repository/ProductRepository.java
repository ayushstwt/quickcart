package com.tier3Hub.quickCart.repository;

import com.tier3Hub.quickCart.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameLike(String keyword, Pageable pageDetails);
}
