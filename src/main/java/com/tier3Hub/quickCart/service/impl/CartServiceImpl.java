package com.tier3Hub.quickCart.service.impl;

import com.tier3Hub.quickCart.dto.CartDTO;
import com.tier3Hub.quickCart.dto.ProductDto;
import com.tier3Hub.quickCart.entity.Cart;
import com.tier3Hub.quickCart.entity.CartItem;
import com.tier3Hub.quickCart.entity.Product;
import com.tier3Hub.quickCart.exception.ResourceNotFoundException;
import com.tier3Hub.quickCart.repository.CartItemRepository;
import com.tier3Hub.quickCart.repository.CartRepository;
import com.tier3Hub.quickCart.repository.ProductRepository;
import com.tier3Hub.quickCart.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartDTO addProductToCart(Long cartId, Long productId, Integer quantity) {

        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart is not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not found"));

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem != null) {
            throw new RuntimeException("Product is already exist.");
        }

        if (product.getQuantity() == 0) {
            throw new RuntimeException("Product is not present.");
        }

        if (product.getQuantity() < quantity) {
            throw new
                    RuntimeException("Product quantity is not sufficient.");}

        CartItem newCartItem = new CartItem();

        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(quantity);
        newCartItem.setDiscount(product.getDiscount());
        newCartItem.setProductPrice(product.getSpecialPrice());

        cartItemRepo.save(newCartItem);

        product.setQuantity(product.getQuantity() - quantity);

        cart.setTotalPrice(cart.getTotalPrice() + (product.getSpecialPrice() * quantity));

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        List<ProductDto> productDTOs = cart.getCartItems().stream()
                .map(p -> modelMapper.map(p.getProduct(), ProductDto.class)).collect(Collectors.toList());

        cartDTO.setProducts(productDTOs);

        return cartDTO;

    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepo.findAll();

        if (carts.size() == 0) {
            throw new RuntimeException("No cart is created till now");
        }

        List<CartDTO> cartDTOs = carts.stream().map(cart -> {
            CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

            List<ProductDto> products = cart.getCartItems().stream()
                    .map(p -> modelMapper.map(p.getProduct(), ProductDto.class)).collect(Collectors.toList());

            cartDTO.setProducts(products);

            return cartDTO;

        }).collect(Collectors.toList());

        return cartDTOs;
    }

    @Override
    public CartDTO getCart(String emailId, Long cartId) {
        Cart cart = cartRepo.findCartByEmailAndCartId(emailId, cartId);

        if (cart == null) {
            throw new ResourceNotFoundException("Cart is not found");
        }

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        List<ProductDto> products = cart.getCartItems().stream()
                .map(p -> modelMapper.map(p.getProduct(), ProductDto.class)).collect(Collectors.toList());

        cartDTO.setProducts(products);

        return cartDTO;
    }

    @Override
    public void updateProductInCarts(Long cartId, Long productId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart is not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not found"));

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new RuntimeException("Product is not available in the cart");
        }

        double cartPrice = cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity());

        cartItem.setProductPrice(product.getSpecialPrice());

        cart.setTotalPrice(cartPrice + (cartItem.getProductPrice() * cartItem.getQuantity()));

        cartItem = cartItemRepo.save(cartItem);
    }

    @Override
    public CartDTO updateProductQuantityInCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("cart is not found!"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not found!"));

        if (product.getQuantity() == 0) {
            throw new RuntimeException("Product is not present.");
        }

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Product quantity is not sufficient.");
        }

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new RuntimeException("Product is not available in the cart");
        }

        double cartPrice = cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity());

        product.setQuantity(product.getQuantity() + cartItem.getQuantity() - quantity);

        cartItem.setProductPrice(product.getSpecialPrice());
        cartItem.setQuantity(quantity);
        cartItem.setDiscount(product.getDiscount());

        cart.setTotalPrice(cartPrice + (cartItem.getProductPrice() * quantity));

        cartItem = cartItemRepo.save(cartItem);

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        List<ProductDto> productDTOs = cart.getCartItems().stream()
                .map(p -> modelMapper.map(p.getProduct(), ProductDto.class)).collect(Collectors.toList());

        cartDTO.setProducts(productDTOs);

        return cartDTO;

    }

    @Override
    public String deleteProductFromCart(Long cartId, int productId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart is not found"));

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new ResourceNotFoundException("Product is not found");
        }

        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity()));

        Product product = cartItem.getProduct();
        product.setQuantity(product.getQuantity() + cartItem.getQuantity());

        cartItemRepo.deleteCartItemByProductIdAndCartId(cartId, productId);

        return "Product is removed from the cart !!!";
    }
}
