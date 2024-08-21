package com.tier3Hub.quickCart.controller;

import com.tier3Hub.quickCart.dto.AddressResponse;
import com.tier3Hub.quickCart.dto.CreateAddressDto;
import com.tier3Hub.quickCart.dto.UpdateAddressDto;
import com.tier3Hub.quickCart.service.AddressService;
import com.tier3Hub.quickCart.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/addresses")
    public ResponseEntity<Object> createAddress(@Valid @RequestBody CreateAddressDto createAddressDto) {
        AddressResponse addressResponse = addressService.createAddress(createAddressDto);
        return ResponseHandler.generateResponse("Address created successfully", HttpStatus.CREATED, addressResponse);
    }

    @GetMapping("/addresses")
    public ResponseEntity<Object> getAddresses() {
        List<AddressResponse> addressDTOs = addressService.getAddresses();
        return ResponseHandler.generateResponse("Addresses fetched successfully", HttpStatus.FOUND, addressDTOs);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<Object> getAddress(@PathVariable Long addressId) {
        AddressResponse addressResponse = addressService.getAddress(addressId);
        return ResponseHandler.generateResponse("Address fetched successfully", HttpStatus.FOUND, addressResponse);
    }

    @PutMapping("/addresses")
    public ResponseEntity<Object> updateAddress(@PathVariable Long addressId, @RequestBody UpdateAddressDto updateAddressDto) {
        AddressResponse addressResponse = addressService.updateAddress(updateAddressDto);
        return ResponseHandler.generateResponse("Address updated successfully", HttpStatus.OK, addressResponse);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseHandler.generateResponse("Address deleted successfully", HttpStatus.OK, null);
    }
}
