package com.tier3Hub.quickCart.repository;

import com.tier3Hub.quickCart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
}
