package com.tier3Hub.quickCart.service;

import com.tier3Hub.quickCart.dto.UserDto;
import com.tier3Hub.quickCart.dto.UserResponse;
import com.tier3Hub.quickCart.entity.User;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDTO);

    List<UserResponse> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    UserDto getUserById(Long userId);

    UserDto updateUser(Long userId, UserDto userDTO);

    String deleteUser(Long userId);

    boolean saveNewUser(User user);

    void saveAdminUser(User user);
}
