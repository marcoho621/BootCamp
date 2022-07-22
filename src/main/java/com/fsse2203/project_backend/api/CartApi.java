package com.fsse2203.project_backend.api;

import com.fsse2203.project_backend.config.EnvConfig;
import com.fsse2203.project_backend.data.cart.CartItemDetailsData;
import com.fsse2203.project_backend.data.cart.dto.CartItemResponseDto;
import com.fsse2203.project_backend.data.cart.dto.GetAllCartItemResponseDto;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.CartItemNotFoundException;
import com.fsse2203.project_backend.exception.InvalidProductQuantityException;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.exception.UserNotFoundException;
import com.fsse2203.project_backend.service.CartItemService;
import com.fsse2203.project_backend.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl}, maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartApi {
    private final CartItemService cartItemService;
    @Autowired
    public CartApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }
    private SecurityUtil secutity = new SecurityUtil();
    Logger logger = LoggerFactory.getLogger(CartApi.class);

    @PutMapping("/add-item/{pid}/{quantity}")
    public CartItemResponseDto addProduct(Authentication authentication, @PathVariable Integer pid, @PathVariable Integer quantity) throws UserNotFoundException, ProductNotFoundException, InvalidProductQuantityException {
        UserEntity user = cartItemService.loginRequired(secutity.getFirebaseUid(authentication));
        if(user == null) {
            logger.warn("user not found.");
            throw new UserNotFoundException();
        }
        return new CartItemResponseDto(cartItemService.addCartItem(user,pid,quantity));
    }

    @GetMapping
    public List<GetAllCartItemResponseDto> getUserCart(Authentication authentication) throws UserNotFoundException {
        UserEntity user = cartItemService.loginRequired(secutity.getFirebaseUid(authentication));
        if(user == null) {
            logger.warn("user not found.");
            throw new UserNotFoundException();
        }
        List<GetAllCartItemResponseDto> userCartResponseList = new ArrayList<>();
        for(CartItemDetailsData cart : cartItemService.getUserCart(user)){
            userCartResponseList.add(new GetAllCartItemResponseDto(cart));
        }
        return userCartResponseList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public GetAllCartItemResponseDto updateUserCart(Authentication authentication,
                                                    @PathVariable Integer pid,
                                                    @PathVariable Integer quantity) throws UserNotFoundException, ProductNotFoundException, InvalidProductQuantityException, CartItemNotFoundException {
        UserEntity user = cartItemService.loginRequired(secutity.getFirebaseUid(authentication));
        if(user == null) {
            logger.warn("user not found.");
            throw new UserNotFoundException();
        }
        return new GetAllCartItemResponseDto(cartItemService.updateCartItem(user, pid, quantity));
    }

    @DeleteMapping("/{pid}")
    public CartItemResponseDto deleteUserCart(Authentication authentication, @PathVariable Integer pid) throws UserNotFoundException, CartItemNotFoundException, ProductNotFoundException {
        UserEntity user = cartItemService.loginRequired(secutity.getFirebaseUid(authentication));
        if(user == null) {
            logger.warn("user not found.");
            throw new UserNotFoundException();
        }
        return new CartItemResponseDto(cartItemService.removeCartItem(user,pid));
    }

}
