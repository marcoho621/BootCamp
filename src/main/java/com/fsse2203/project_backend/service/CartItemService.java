package com.fsse2203.project_backend.service;

import com.fsse2203.project_backend.data.cart.CartItemData;
import com.fsse2203.project_backend.data.cart.CartItemDetailsData;
import com.fsse2203.project_backend.data.cart.entity.CartItemEntity;
import com.fsse2203.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.CartItemNotFoundException;
import com.fsse2203.project_backend.exception.InvalidProductQuantityException;
import com.fsse2203.project_backend.exception.ProductNotFoundException;

import java.util.List;

public interface CartItemService {
    UserEntity loginRequired(String firebaseUid);
    CartItemData addCartItem(UserEntity user, Integer pid, Integer quantity)throws ProductNotFoundException, InvalidProductQuantityException;
    List<CartItemDetailsData> getUserCart(UserEntity user);
    CartItemDetailsData updateCartItem(UserEntity user, Integer pid, Integer quantity)throws ProductNotFoundException, InvalidProductQuantityException, CartItemNotFoundException;
    CartItemData removeCartItem(UserEntity user, Integer pid)throws CartItemNotFoundException,ProductNotFoundException;
    void clearCartItems(TransactionEntity transactionEntity) throws ProductNotFoundException, CartItemNotFoundException;
    List<CartItemEntity> getCartItemEntityBUser(UserEntity user);
    boolean addCartItemToTransacion(CartItemEntity cartItemEntity);
}
