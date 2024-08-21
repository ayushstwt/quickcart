package com.tier3Hub.quickCart.controller;

import com.tier3Hub.quickCart.dto.*;
import com.tier3Hub.quickCart.entity.User;
import com.tier3Hub.quickCart.exception.UserNotFoundException;
import com.tier3Hub.quickCart.security.JWTUtil;
import com.tier3Hub.quickCart.security.UserInfoConfigManager;
import com.tier3Hub.quickCart.service.UserService;
import com.tier3Hub.quickCart.utils.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserInfoConfigManager userInfoConfigManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Object> signup(@Valid@RequestBody RegisterDto registerDto) {
        RegisterResponse registerResponse = service.saveNewUser(registerDto);
        return ResponseHandler.generateResponse("User registered successfully", HttpStatus.OK, registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
            UserDetails userDetails = userInfoConfigManager.loadUserByUsername(loginDto.getUsernameOrEmail());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            LoginResponse build = LoginResponse.builder().token(jwt).build();
            return ResponseHandler.generateResponse("User logged in successfully", HttpStatus.OK, build);
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registerAdmin")
    public void signupAdmin(@RequestBody User user) {
        service.saveAdminUser(user);
    }



}
