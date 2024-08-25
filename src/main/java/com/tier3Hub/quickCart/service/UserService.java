package com.tier3Hub.quickCart.service;

import com.tier3Hub.quickCart.dto.*;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    UserDto getUserById(Long userId);

    UserDto updateUser(Long userId, UserDto userDTO);

    String deleteUser(Long userId);

    RegisterResponse saveNewUser(RegisterDto registerDto);

    RegisterResponse saveAdminUser(RegisterDto registerDto);

    void updateUser(UserResponse userResponse);
}
