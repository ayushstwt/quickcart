package com.tier3Hub.quickCart.service.impl;

import com.tier3Hub.quickCart.dto.*;
import com.tier3Hub.quickCart.entity.Cart;
import com.tier3Hub.quickCart.entity.User;
import com.tier3Hub.quickCart.repository.AddressRepository;
import com.tier3Hub.quickCart.repository.UserRepository;
import com.tier3Hub.quickCart.security.JWTUtil;
import com.tier3Hub.quickCart.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;



    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegisterResponse saveNewUser(RegisterDto registerDto) {
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            User save = userRepository.save(user);
            RegisterResponse successfully = RegisterResponse.builder()
                    .userId(save.getUserId()).username(save.getUsername())
                    .email(save.getEmail()).build();
            return successfully;

    }

    @Override
    public RegisterResponse saveAdminUser(RegisterDto registerDto) {
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN","USER"));
        User save = userRepository.save(user);
        RegisterResponse successfully = RegisterResponse.builder()
                .userId(save.getUserId()).username(save.getUsername())
                .email(save.getEmail()).build();
        return successfully;
    }

    @Override
    public void updateUser(UserResponse userResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName);

    }


//    @Override
//    public UserDto registerUser(UserDto userDTO) {
//        try {
//            User user = modelMapper.map(userDTO, User.class);
//
//            Cart cart = new Cart();
//            user.setCart(cart);
//
//          Role role = roleRepository.findById(AppConstants.USER_ID).get();
//          user.getRoles().add(role);
//
//            String country = userDTO.getAddress().getCountry();
//            String state = userDTO.getAddress().getState();
//            String city = userDTO.getAddress().getCity();
//            String postalCode = userDTO.getAddress().getZipCode();
//
//            Addresses address = addressRepository.findByCountryAndStateAndCityAndPostalCode(country,state,city,postalCode);
//
//            if (address == null) {
//                address =new Addresses();
//                address.setAddress1(userDTO.getAddress().getAddressLine1());
//                address.setAddress2(userDTO.getAddress().getAddressLine2());
//                address.setCity(city);
//                address.setCountry(country);
//                address.setPostalCode(postalCode);
//                address = addressRepository.save(address);
//            }
//
//            user.setAddresses(List.of(address));
//            User registeredUser = userRepository.save(user);
//
//            cart.setUser(registeredUser);
//
//            userDTO = modelMapper.map(registeredUser, UserDto.class);
//
//          userDTO.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDto.class));
//
//          return userDTO;
//        } catch (DataIntegrityViolationException e) {
//            throw new RuntimeException("User already exists with emailId: " + userDTO.getEmail());
//        }
//    }



    @Override
    public List<UserDto> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDto.class));
        CartDTO cartDTO=modelMapper.map(user.getCart(),CartDTO.class);
        userDto.setCart(cartDTO);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDTO) {
        return null;
    }

    @Override
    public String deleteUser(Long userId) {
        return "";
    }

}
