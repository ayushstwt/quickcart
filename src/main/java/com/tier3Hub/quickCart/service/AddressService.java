package com.tier3Hub.quickCart.service;

import com.tier3Hub.quickCart.dto.AddressResponse;
import com.tier3Hub.quickCart.dto.CreateAddressDto;
import com.tier3Hub.quickCart.dto.UpdateAddressDto;

import java.util.List;

public interface AddressService {
    AddressResponse createAddress(CreateAddressDto createAddressDto);

    AddressResponse getAddress(Long addressId);

    List<AddressResponse> getAddresses();

    void deleteAddress(Long addressId);

    AddressResponse updateAddress(UpdateAddressDto updateAddressDto);
}
