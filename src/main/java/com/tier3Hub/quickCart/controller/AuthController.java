package com.tier3Hub.quickCart.controller;

import com.tier3Hub.quickCart.dto.UserDto;
import com.tier3Hub.quickCart.entity.User;
import com.tier3Hub.quickCart.exception.UserNotFoundException;
import com.tier3Hub.quickCart.security.JWTUtil;
import com.tier3Hub.quickCart.security.UserInfoConfigManager;
import com.tier3Hub.quickCart.service.UserService;
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
    public void signup(@RequestBody User user) {
        service.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userInfoConfigManager.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registerAdmin")
    public void signupAdmin(@RequestBody User user) {
        service.saveAdminUser(user);
    }



}
