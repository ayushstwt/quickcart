package com.tier3Hub.quickCart.repository;

import com.tier3Hub.quickCart.entity.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Addresses, Long> {
    Addresses findByCountryAndStateAndCityAndPostalCode(String country, String state, String city, String postalCode);
}
