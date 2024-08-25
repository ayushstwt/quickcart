package com.tier3Hub.quickCart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;
    private AddressDto address;
    private CartDTO cart;
}
