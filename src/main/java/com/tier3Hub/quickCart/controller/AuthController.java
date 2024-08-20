package com.tier3Hub.quickCart.controller;

import com.tier3Hub.quickCart.dto.UserDto;
import com.tier3Hub.quickCart.entity.User;
import com.tier3Hub.quickCart.exception.UserNotFoundException;
import com.tier3Hub.quickCart.security.JWTService;
import com.tier3Hub.quickCart.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerHandler(@Valid @RequestBody UserDto user) throws UserNotFoundException {
        String encodedPass = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPass);

        UserDto userDTO = service.registerUser(user);

        String token = jwtService.generateToken(userDTO.getEmail());

        return new ResponseEntity<Map<String, Object>>(Collections.singletonMap("jwt-token", token),
                HttpStatus.CREATED);
    }



}
