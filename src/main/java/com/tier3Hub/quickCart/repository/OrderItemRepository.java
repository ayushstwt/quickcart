package com.tier3Hub.quickCart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<com.tier3Hub.quickCart.entity.OrderItem, Long> {
}
