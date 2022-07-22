package com.fsse2203.project_backend.api;

import com.fsse2203.project_backend.config.EnvConfig;
import com.fsse2203.project_backend.data.transaction.dto.TransactionDetailResponseDto;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.*;
import com.fsse2203.project_backend.service.TransactionService;
import com.fsse2203.project_backend.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl}, maxAge = 3600)
@RestController
@RequestMapping("/transaction")
public class TransactionApi {
    private SecurityUtil secutity = new SecurityUtil();
    Logger logger = LoggerFactory.getLogger(TransactionApi.class);
    private final TransactionService transactionService;
    @Autowired
    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionDetailResponseDto createNewTransaction(Authentication authentication) throws UserNotFoundException, CartItemNotFoundException {
        UserEntity user = userLogin(authentication);
        return new TransactionDetailResponseDto(transactionService.createTransaction(user));
    }

    @GetMapping("/{tid}")
    public TransactionDetailResponseDto getUserTransaction(Authentication authentication, @PathVariable Integer tid) throws UserNotFoundException, TransactionNotFoundException, InvalidUserException {
        UserEntity user = userLogin(authentication);
        return new TransactionDetailResponseDto(transactionService.getTransaction(user,tid));
    }

    @PatchMapping("/{tid}/pay")
    public TransactionDetailResponseDto transactionPayment(Authentication authentication, @PathVariable Integer tid) throws UserNotFoundException, CartItemNotFoundException, InvalidTransactionStatusException, TransactionNotFoundException, InvalidUserException, ProductNotFoundException, InvalidProductQuantityException {
        UserEntity user = userLogin(authentication);
        return new TransactionDetailResponseDto(transactionService.transactionPayStatus(user,tid));
    }

    @PatchMapping("/{tid}/finish")
    public TransactionDetailResponseDto transactionFinish(Authentication authentication, @PathVariable Integer tid) throws UserNotFoundException, CartItemNotFoundException, InvalidTransactionStatusException, TransactionNotFoundException, InvalidUserException, ProductNotFoundException {
        UserEntity user = userLogin(authentication);
        return new TransactionDetailResponseDto(transactionService.transactionFinishStatus(user,tid));
    }

    public UserEntity userLogin(Authentication authentication) throws UserNotFoundException {
        UserEntity user = transactionService.loginRequired(secutity.getFirebaseUid(authentication));
        if(user == null) {
            logger.warn("user not found.");
            throw new UserNotFoundException();
        }
        logger.info("user login.");
        return user;
    }
}
