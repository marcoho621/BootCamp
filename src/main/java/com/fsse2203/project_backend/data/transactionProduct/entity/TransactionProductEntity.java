package com.fsse2203.project_backend.data.transactionProduct.entity;

import com.fsse2203.project_backend.data.cart.CartItemDetailsData;
import com.fsse2203.project_backend.data.cart.entity.CartItemEntity;
import com.fsse2203.project_backend.data.transaction.entity.TransactionEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction_product")
public class TransactionProductEntity {
    @Id
    @Column(name = "tpid",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;

    @ManyToOne
    @JoinColumn(name = "transaction_id",nullable = false)
    private TransactionEntity transaction;

    @JoinColumn(name = "product_id",nullable = false)
    private Integer pid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "subtotal",nullable = false)
    private BigDecimal subtotal;

    public TransactionProductEntity() {
    }

    public TransactionProductEntity(CartItemDetailsData data) {
        setPid(data.getProduct().getPid());
        setName(data.getProduct().getName());
        setImageUrl(data.getProduct().getImageUrl());
        setPrice(data.getProduct().getPrice());
        setDescription(data.getProduct().getDescription());
        setStock(data.getProduct().getStock());
        setQuantity(data.getQuantity());
        setSubtotal(data.getProduct().getPrice(),data.getQuantity());
    }

    public TransactionProductEntity(TransactionEntity transaction, CartItemEntity cartItemEntity){
        setTransaction(transaction);
        setPid(cartItemEntity.getProduct().getPid());
        setName(cartItemEntity.getProduct().getName());
        setImageUrl(cartItemEntity.getProduct().getImageUrl());
        setPrice(cartItemEntity.getProduct().getPrice());
        setDescription(cartItemEntity.getProduct().getDescription());
        setStock(cartItemEntity.getProduct().getStock());
        setQuantity(cartItemEntity.getQuantity());
        setSubtotal(cartItemEntity.getProduct().getPrice(), cartItemEntity.getQuantity());
    }

    public TransactionProductEntity(CartItemEntity cartData, Integer tpid) {
        setPid(cartData.getProduct().getPid());
        setName(cartData.getProduct().getName());
        setImageUrl(cartData.getProduct().getImageUrl());
        setPrice(cartData.getProduct().getPrice());
        setDescription(cartData.getProduct().getDescription());
        setStock(cartData.getProduct().getStock());
        setQuantity(cartData.getQuantity());
        setSubtotal(cartData.getProduct().getPrice(),cartData.getQuantity());
        setTpid(tpid);
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal price,Integer quantity) {
        this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
    }

    public Integer getTpid() {
        return tpid;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }
}
