package com.fsse2203.project_backend.service.impl;

import com.fsse2203.project_backend.data.PaymentStatus;
import com.fsse2203.project_backend.data.cart.entity.CartItemEntity;
import com.fsse2203.project_backend.data.transaction.TransactionDetailData;
import com.fsse2203.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2203.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.*;
import com.fsse2203.project_backend.repository.TransactionRepository;
import com.fsse2203.project_backend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final UserService userService;
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final TransactionRepository transactionRepository;
    private final TransactionProductService transactionProductService;
    @Autowired
    public TransactionServiceImpl(UserService userService, ProductService productService, CartItemService cartItemService, TransactionRepository transactionRepository, TransactionProductService transactionProductService) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.transactionRepository = transactionRepository;
        this.transactionProductService = transactionProductService;
    }

    @Override
    public UserEntity loginRequired(String firebaseUid) {
        return userService.getUserEntityByFirebaseUid(firebaseUid);
    }

    @Override
    public TransactionDetailData createTransaction(UserEntity user) throws CartItemNotFoundException {
        List<CartItemEntity> prepareCartItem = prepareCartItem(cartItemService.getCartItemEntityBUser(user));
        if(prepareCartItem.isEmpty()) {
            logger.warn("prepare cart is null");
            throw new CartItemNotFoundException();
        }
        TransactionEntity transaction = new TransactionEntity(user);
        transaction.setTotal(calculateTotal(prepareCartItem));
        TransactionEntity transactionEntity = transactionRepository.save(transaction);
        logger.info("create new transaction.");
        List<TransactionProductEntity> cart = transactionProductService.findUserCartToSaveTransactionProduct(prepareCartItem,transaction);
        transactionEntity.setTransactionProductEntity(cart);
        return new TransactionDetailData(transactionEntity);
    }

    @Override
    public TransactionDetailData getTransaction(UserEntity user, Integer tid) throws TransactionNotFoundException, InvalidUserException {
        TransactionEntity getTransaction = transactionRepository.findByTid(tid);
        userTransactionChecking(getTransaction,user);
        return new TransactionDetailData(getTransaction);
    }

    @Override
    public TransactionDetailData transactionPayStatus(UserEntity user, Integer tid) throws TransactionNotFoundException, InvalidUserException, ProductNotFoundException, InvalidTransactionStatusException, InvalidProductQuantityException {
        TransactionEntity getTransaction = transactionRepository.findByTid(tid);
        userTransactionChecking(getTransaction,user);
        if(getTransaction.getStatus() == PaymentStatus.PREPARE){
            for(TransactionProductEntity product : getTransaction.
                    getTransactionProductEntity()){
                productService.deduceProductStock(product.getPid(),product.getQuantity());
            }
            getTransaction.setStatus(PaymentStatus.PROCESSING);
            return new TransactionDetailData(transactionRepository.save(getTransaction));
        }
        throw new InvalidTransactionStatusException();
    }

    @Override
    public TransactionDetailData transactionFinishStatus(UserEntity user, Integer tid) throws TransactionNotFoundException, InvalidUserException, CartItemNotFoundException, ProductNotFoundException, InvalidTransactionStatusException {
        TransactionEntity getTransaction = transactionRepository.findByTid(tid);
        userTransactionChecking(getTransaction,user);
        if(getTransaction.getStatus() == PaymentStatus.PROCESSING){
            cartItemService.clearCartItems(getTransaction);
            logger.info("empty cart.");
            getTransaction.setStatus(PaymentStatus.SUCCESS);
            logger.info("update transaction status to success.");
            return new TransactionDetailData(transactionRepository.save(getTransaction));
        }
        logger.warn("invalid transaction status.");
        throw new InvalidTransactionStatusException();
    }

    public BigDecimal calculateTotal(List<CartItemEntity> cartItemList) {
        BigDecimal result = new BigDecimal(0);
        for(CartItemEntity cartItem : cartItemList){
            result = result.add(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        return result;
    }

    private void userTransactionChecking(TransactionEntity transactionEntity, UserEntity user) throws TransactionNotFoundException, InvalidUserException {
        if(transactionEntity == null) {
            logger.warn("transaction not found.");
            throw new TransactionNotFoundException();
        }
        if(!transactionEntity.getUser().equals(user)) {
            logger.warn("user not found.");
            throw new InvalidUserException();
        }
    }

    private List<CartItemEntity> prepareCartItem(List<CartItemEntity> cartItemList){
        List<CartItemEntity> result = new ArrayList<>();
        for(CartItemEntity cartEntity : cartItemList){
            logger.info("Test cartData.isProccessed: " + cartEntity.isProccessed());
            if(cartItemService.addCartItemToTransacion(cartEntity)){
                result.add(cartEntity);
            }
        }
        return result;
    }
}
