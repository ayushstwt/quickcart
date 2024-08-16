package com.tier3Hub.quickCart.repository;

import com.tier3Hub.quickCart.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
