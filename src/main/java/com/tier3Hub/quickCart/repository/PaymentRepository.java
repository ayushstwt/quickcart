package com.tier3Hub.quickCart.repository;

import com.tier3Hub.quickCart.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
