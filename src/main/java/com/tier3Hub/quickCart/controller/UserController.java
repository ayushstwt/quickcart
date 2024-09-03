package com.tier3Hub.quickCart.controller;

import com.tier3Hub.quickCart.dto.UserResponse;
import com.tier3Hub.quickCart.entity.User;
import com.tier3Hub.quickCart.service.UserService;
import com.tier3Hub.quickCart.utils.AppConstants;
import com.tier3Hub.quickCart.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<Object> getAllUser(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    )
    {
        return ResponseHandler.generateResponse("Users retrieved successfully", HttpStatus.FOUND, userService.getAllUsers(pageNumber,pageSize,sortBy,sortOrder));
    }

    public ResponseEntity<Object> getUserById(Long userId)
    {
        return ResponseHandler.generateResponse("User retrieved successfully", HttpStatus.FOUND, userService.getUserById(userId));
    }

    public ResponseEntity<Object> deleteUser(Long userId)
    {
        return ResponseHandler.generateResponse("User deleted successfully", HttpStatus.OK, userService.deleteUser(userId));
    }

    public ResponseEntity<Object> updateUser(UserResponse userResponse)
    {

        userService.updateUser(userResponse);
        return ResponseHandler.generateResponse("User updated successfully", HttpStatus.OK, null);
    }
}
