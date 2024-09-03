package com.tier3Hub.quickCart.service.impl;

import com.tier3Hub.quickCart.dto.AddressResponse;
import com.tier3Hub.quickCart.dto.CreateAddressDto;
import com.tier3Hub.quickCart.dto.UpdateAddressDto;
import com.tier3Hub.quickCart.entity.Addresses;
import com.tier3Hub.quickCart.repository.AddressRepository;
import com.tier3Hub.quickCart.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AddressResponse createAddress(CreateAddressDto createAddressDto) {
        String country = createAddressDto.getCountry();
        String state = createAddressDto.getState();
        String city = createAddressDto.getCity();
        String pinCode = createAddressDto.getPostalCode();
        Addresses byCountryAndStateAndCityAndPostalCode = addressRepository.findByCountryAndStateAndCityAndPostalCode(country, state, city, pinCode);
        if (byCountryAndStateAndCityAndPostalCode == null) {
            Addresses addresses = new Addresses();
            addresses.setAddress1(createAddressDto.getAddressLine1());
            addresses.setAddress2(createAddressDto.getAddressLine2());
            addresses.setCity(createAddressDto.getCity());
            addresses.setState(createAddressDto.getState());
            addresses.setCountry(createAddressDto.getCountry());
            addresses.setPostalCode(createAddressDto.getPostalCode());
            Addresses save = addressRepository.save(addresses);
            return modelMapper.map(save, AddressResponse.class);
        }
        throw new RuntimeException("Address already exists");
    }

    @Override
    public AddressResponse getAddress(Long addressId) {
        Addresses addresses = addressRepository.findById(addressId).orElseThrow(() -> new RuntimeException("Address not found"));
        return modelMapper.map(addresses, AddressResponse.class);
    }

    @Override
    public List<AddressResponse> getAddresses() {
        List<Addresses> addresses = addressRepository.findAll();
        return addresses.stream().map(address -> modelMapper.map(address, AddressResponse.class)).toList();
    }

    @Override
    public void deleteAddress(Long addressId) {
        Addresses addresses = addressRepository.findById(addressId).orElseThrow(() -> new RuntimeException("Address not found"));
        addressRepository.delete(addresses);
    }

    @Override
    public AddressResponse updateAddress(UpdateAddressDto updateAddressDto) {
        Addresses addresses = addressRepository.findById(updateAddressDto.getId()).orElseThrow(() -> new RuntimeException("Address not found"));
        addresses.setAddress1(updateAddressDto.getAddressLine1());
        addresses.setAddress2(updateAddressDto.getAddressLine2());
        addresses.setCity(updateAddressDto.getCity());
        addresses.setState(updateAddressDto.getState());
        addresses.setCountry(updateAddressDto.getCountry());
        addresses.setPostalCode(updateAddressDto.getPostalCode());
        Addresses save = addressRepository.save(addresses);
        return modelMapper.map(save, AddressResponse.class);
    }
}
