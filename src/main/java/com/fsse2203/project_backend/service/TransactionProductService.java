package com.fsse2203.project_backend.service;

import com.fsse2203.project_backend.data.cart.entity.CartItemEntity;
import com.fsse2203.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2203.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.CartItemNotFoundException;

import java.util.List;

public interface TransactionProductService {
    UserEntity loginRequired(String firebaseUid);
    List<TransactionProductEntity> findUserCartToSaveTransactionProduct(List<CartItemEntity> cartItemList, TransactionEntity entity);
}
