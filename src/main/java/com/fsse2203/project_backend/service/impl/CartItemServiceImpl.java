package com.fsse2203.project_backend.service.impl;

import com.fsse2203.project_backend.data.cart.CartItemData;
import com.fsse2203.project_backend.data.cart.CartItemDetailsData;
import com.fsse2203.project_backend.data.cart.entity.CartItemEntity;
import com.fsse2203.project_backend.data.product.entity.ProductEntity;
import com.fsse2203.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2203.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.CartItemNotFoundException;
import com.fsse2203.project_backend.exception.InvalidProductQuantityException;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.repository.CartItemRepository;
import com.fsse2203.project_backend.service.CartItemService;
import com.fsse2203.project_backend.service.ProductService;
import com.fsse2203.project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public UserEntity loginRequired(String firebaseUid) {
        return userService.getUserEntityByFirebaseUid(firebaseUid);
    }

    @Override
    public CartItemData addCartItem(UserEntity user, Integer pid, Integer quantity) throws ProductNotFoundException, InvalidProductQuantityException {
        ProductEntity product = productService.getProductByPid(pid);
        if(product.getStock() < 1) {
            logger.warn("product out of stock.");
            throw new ProductNotFoundException();
        }
        CartItemEntity userCartItem = cartItemRepository.findByUserAndProduct(user,product);
        if(userCartItem != null){
            cartItemRepository.delete(userCartItem);
        }
        checkQuantityIsValid(quantity);
        cartItemRepository.save(new CartItemEntity(product,user,quantity));
        logger.info("create new cart item.");
        return new CartItemData();
    }

    @Override
    public List<CartItemDetailsData> getUserCart(UserEntity user) {
        List<CartItemDetailsData> userCartList = new ArrayList<>();
        for(CartItemEntity cartItem : cartItemRepository.findByUser(user)){
            if(!cartItem.isProccessed()){
                userCartList.add(new CartItemDetailsData(cartItem));
            }
        }
        return userCartList;
    }

    @Override
    public List<CartItemEntity> getCartItemEntityBUser(UserEntity user){
        return cartItemRepository.findByUser(user);
    }

    @Override
    public CartItemDetailsData updateCartItem(UserEntity user, Integer pid, Integer quantity) throws ProductNotFoundException, InvalidProductQuantityException, CartItemNotFoundException {
        checkQuantityIsValid(quantity);
        ProductEntity product = productService.getProductByPid(pid);
        CartItemEntity userCartItem = cartItemRepository.findByUserAndProduct(user,product);
        if(userCartItem == null) {
            logger.warn("user cart item not found.");
            throw new CartItemNotFoundException();
        }
        if(quantity > product.getStock()) quantity = product.getStock();
        userCartItem.setQuantity(quantity);
        cartItemRepository.save(userCartItem);
        logger.info("update cart item success.");
        return new CartItemDetailsData(userCartItem);
    }

    @Override
    public CartItemData removeCartItem(UserEntity user, Integer pid) throws ProductNotFoundException, CartItemNotFoundException {
        ProductEntity product = productService.getProductByPid(pid);
        CartItemEntity userCartItem = cartItemRepository.findByUserAndProduct(user,product);
        if(userCartItem == null) {
            logger.warn("cart item not found.");
            throw new CartItemNotFoundException();
        }
        cartItemRepository.delete(userCartItem);
        return new CartItemData();
    }

    @Override
    public void clearCartItems(TransactionEntity transactionEntity) throws ProductNotFoundException, CartItemNotFoundException {
        for(TransactionProductEntity product : transactionEntity.getTransactionProductEntity()){
            removeCartItem(transactionEntity.getUser(),product.getPid());
        }
    }

    @Override
    public boolean addCartItemToTransacion(CartItemEntity cartItemEntity){
        if(!cartItemEntity.isProccessed()){
            cartItemEntity.setProccessed(true);
            cartItemRepository.save(cartItemEntity);
            return true;
        }
        return false;
    }

    private void checkQuantityIsValid(Integer quantity) throws InvalidProductQuantityException {
        if(quantity < 0){
            logger.warn("invalid quantity.");
            throw new InvalidProductQuantityException();
        }
    }
}
