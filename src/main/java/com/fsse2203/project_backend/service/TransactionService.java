package com.fsse2203.project_backend.service;

import com.fsse2203.project_backend.data.transaction.TransactionDetailData;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.*;

public interface TransactionService {
    UserEntity loginRequired(String firebaseUid);
    TransactionDetailData createTransaction(UserEntity user) throws CartItemNotFoundException;
    TransactionDetailData getTransaction(UserEntity user, Integer tid) throws TransactionNotFoundException, InvalidUserException;
    TransactionDetailData transactionPayStatus(UserEntity user, Integer tid) throws TransactionNotFoundException, InvalidUserException, ProductNotFoundException, InvalidTransactionStatusException, InvalidProductQuantityException;
    TransactionDetailData transactionFinishStatus(UserEntity user, Integer tid) throws TransactionNotFoundException, InvalidUserException, CartItemNotFoundException, ProductNotFoundException, InvalidTransactionStatusException;
}
