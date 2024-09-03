package com.tier3Hub.quickCart.controller;

import com.tier3Hub.quickCart.dto.UserDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<Object> getUsers(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_USERS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {

        List<UserDto> userDto = userService.getAllUsers(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<Object>(userDto, HttpStatus.FOUND);
    }

    @GetMapping("/public/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto user = userService.getUserById(userId);

        return new ResponseEntity<UserDto>(user, HttpStatus.FOUND);
    }

    @PutMapping("/public/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDTO, @PathVariable Long userId) {
        UserDto updatedUser = userService.updateUser(userId, userDTO);

        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String status = userService.deleteUser(userId);

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}
