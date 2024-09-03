package com.tier3Hub.quickCart.service.impl;

import com.tier3Hub.quickCart.dto.AddressResponse;
import com.tier3Hub.quickCart.dto.CreateAddressDto;
import com.tier3Hub.quickCart.dto.UpdateAddressDto;
import com.tier3Hub.quickCart.repository.AddressRepository;
import com.tier3Hub.quickCart.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public AddressResponse createAddress(CreateAddressDto createAddressDto) {
        String country = createAddressDto.getCountry();
        String state = createAddressDto.getState();
        String city = createAddressDto.getCity();
        String pincode = createAddressDto.getPostalCode();
        String street = createAddressDto.getStreet();
        String buildingName = addressDTO.getBuildingName();
        return null;
    }

    @Override
    public AddressResponse getAddress(Long addressId) {
        return null;
    }

    @Override
    public List<AddressResponse> getAddresses() {
        return List.of();
    }

    @Override
    public void deleteAddress(Long addressId) {

    }

    @Override
    public AddressResponse updateAddress(UpdateAddressDto updateAddressDto) {
        return null;
    }
}
