package com.tier3Hub.quickCart.service;

import com.tier3Hub.quickCart.dto.*;
import com.tier3Hub.quickCart.entity.User;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDTO);

    List<UserResponse> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    UserDto getUserById(Long userId);

    UserDto updateUser(Long userId, UserDto userDTO);

    String deleteUser(Long userId);

    RegisterResponse saveNewUser(RegisterDto registerDto);

    void saveAdminUser(User user);
}
