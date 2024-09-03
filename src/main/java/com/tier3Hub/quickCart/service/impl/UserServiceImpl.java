package com.tier3Hub.quickCart.service.impl;

import com.tier3Hub.quickCart.dto.*;
import com.tier3Hub.quickCart.entity.Addresses;
import com.tier3Hub.quickCart.entity.CartItem;
import com.tier3Hub.quickCart.entity.User;
import com.tier3Hub.quickCart.exception.ResourceNotFoundException;
import com.tier3Hub.quickCart.repository.AddressRepository;
import com.tier3Hub.quickCart.repository.UserRepository;
import com.tier3Hub.quickCart.security.JWTUtil;
import com.tier3Hub.quickCart.service.CartService;
import com.tier3Hub.quickCart.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private CartService cartService;



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
    public List<UserDto> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<User> pageUsers = userRepository.findAll(pageDetails);

        List<User> users = pageUsers.getContent();

        if (users.size() == 0) {
            throw new RuntimeException("No User exists !!!");
        }

        List<UserDto> userDTOs = users.stream().map(user -> {
            UserDto dto = modelMapper.map(user, UserDto.class);

            if (user.getAddresses().size() != 0) {
                dto.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDto.class));
            }

            CartDTO cart = modelMapper.map(user.getCart(), CartDTO.class);

            List<ProductDto> products = user.getCart().getCartItems().stream()
                    .map(item -> modelMapper.map(item.getProduct(), ProductDto.class)).collect(Collectors.toList());

            dto.setCart(cart);

            dto.getCart().setProducts(products);

            return dto;

        }).collect(Collectors.toList());

        return userDTOs;
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not found with id: " + userId));

        String encodedPass = passwordEncoder.encode(userDTO.getPassword());

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encodedPass);

        if (userDTO.getAddress() != null) {
            String country = userDTO.getAddress().getCountry();
            String state = userDTO.getAddress().getState();
            String city = userDTO.getAddress().getCity();
            String pincode = userDTO.getAddress().getZipCode();
            String street = userDTO.getAddress().getAddressLine1();

            Addresses address = addressRepository.findByCountryAndStateAndCityAndPostalCode(country, state, city, pincode);

            if (address == null) {
                address = new Addresses();
                address.setPostalCode(pincode);
                address.setCountry(country);
                address.setState(state);
                address.setCity(city);

                address = addressRepository.save(address);

                user.setAddresses(List.of(address));
            }
        }

        userDTO = modelMapper.map(user, UserDto.class);

        userDTO.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDto.class));

        CartDTO cart = modelMapper.map(user.getCart(), CartDTO.class);

        List<ProductDto> products = user.getCart().getCartItems().stream()
                .map(item -> modelMapper.map(item.getProduct(), ProductDto.class)).collect(Collectors.toList());

        userDTO.setCart(cart);

        userDTO.getCart().setProducts(products);

        return userDTO;
    }

    @Override
    public String deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not found with id: " + userId));

        List<CartItem> cartItems = user.getCart().getCartItems();
        Long cartId = user.getCart().getCartId();

        cartItems.forEach(item -> {

            int productId = item.getProduct().getId();

            cartService.deleteProductFromCart(cartId, productId);
        });

        userRepository.delete(user);

        return "User with userId " + userId + " deleted successfully!!!";
    }


}
