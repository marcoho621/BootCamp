package com.fsse2203.project_backend.data.transaction.entity;

import com.fsse2203.project_backend.data.PaymentStatus;
import com.fsse2203.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @Column(name = "tid",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity> transactionProductEntity;

    @ManyToOne
    @JoinColumn(name = "uid",nullable = false)
    private UserEntity user;

    @Column(name = "date_time",nullable = false)
    private LocalDateTime dataTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    public TransactionEntity() {
    }

    public TransactionEntity(UserEntity user) {
        setUser(user);
        setDataTime(LocalDateTime.now());
        setStatus(PaymentStatus.PREPARE);
        this.transactionProductEntity = new ArrayList<>();
        this.total = new BigDecimal(0);
    }

    public List<TransactionProductEntity> getTransactionProductEntity() {
        return transactionProductEntity;
    }

    public void setTransactionProductEntity(List<TransactionProductEntity> transactionProductEntity) {
        this.transactionProductEntity.addAll(transactionProductEntity);
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getTid() {
        return tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public void setDataTime(LocalDateTime dataTime) {
        this.dataTime = dataTime;
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
