package com.fsse2203.project_backend.data.transaction;

import com.fsse2203.project_backend.data.PaymentStatus;
import com.fsse2203.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2203.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2203.project_backend.data.user.UserDetailsData;
import com.fsse2203.project_backend.data.user.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailData {
    private Integer tid;
    private UserDetailsData user;
    private LocalDateTime dateTime;
    private PaymentStatus status;
    private BigDecimal total;
    private List<TransactionProductDetailData> items = new ArrayList<>();

    public TransactionDetailData(TransactionEntity entity) {
        setTid(entity.getTid());
        setUser(entity.getUser());
        setDateTime(entity.getDataTime());
        setStatus(entity.getStatus());
        setTotal(entity.getTotal());
        setItems(entity.getTransactionProductEntity());
    }

    public UserDetailsData getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = new UserDetailsData(user);
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public List<TransactionProductDetailData> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductEntity> item) {
        for(TransactionProductEntity cartItem : item) {
            this.items.add(new TransactionProductDetailData(cartItem));
        }
    }

    public Integer getTid() {
        return tid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
