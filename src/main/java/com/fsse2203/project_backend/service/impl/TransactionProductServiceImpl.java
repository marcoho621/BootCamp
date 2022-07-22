package com.fsse2203.project_backend.service.impl;

import com.fsse2203.project_backend.data.cart.CartItemDetailsData;
import com.fsse2203.project_backend.data.cart.entity.CartItemEntity;
import com.fsse2203.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2203.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;

import com.fsse2203.project_backend.exception.CartItemNotFoundException;
import com.fsse2203.project_backend.repository.TransactionProductRepository;
import com.fsse2203.project_backend.service.CartItemService;
import com.fsse2203.project_backend.service.TransactionProductService;
import com.fsse2203.project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
    Logger logger = LoggerFactory.getLogger(TransactionProductServiceImpl.class);
    private final UserService userService;
    private final TransactionProductRepository transactionProductRepository;
    private final CartItemService cartItemService;

    @Autowired
    public TransactionProductServiceImpl(UserService userService, TransactionProductRepository transactionProductRepository, CartItemService cartItemService) {
        this.userService = userService;
        this.transactionProductRepository = transactionProductRepository;
        this.cartItemService = cartItemService;
    }

    @Override
    public UserEntity loginRequired(String firebaseUid) {
        return userService.getUserEntityByFirebaseUid(firebaseUid);
    }

    @Override
    public List<TransactionProductEntity> findUserCartToSaveTransactionProduct(List<CartItemEntity> cartItemList, TransactionEntity entity) {
        List<TransactionProductEntity> cart = new ArrayList<>();
        for (CartItemEntity cartEntity : cartItemList) {
            TransactionProductEntity tpidEntity = transactionProductRepository.save(new TransactionProductEntity(entity, cartEntity));
            cart.add(new TransactionProductEntity(cartEntity, tpidEntity.getTpid()));
            logger.info("add transaction product.");
        }
        return cart;
    }
}
