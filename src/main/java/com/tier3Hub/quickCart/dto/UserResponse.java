package com.tier3Hub.quickCart.dto;

import com.tier3Hub.quickCart.entity.Role;

import java.util.HashSet;
import java.util.Set;

public class UserResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
