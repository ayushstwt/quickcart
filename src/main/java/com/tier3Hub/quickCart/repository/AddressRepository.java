package com.tier3Hub.quickCart.repository;

import com.tier3Hub.quickCart.entity.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Addresses, Long> {

}
