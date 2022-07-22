package com.fsse2203.project_backend.data.cart.entity;

import com.fsse2203.project_backend.data.product.entity.ProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItemEntity {
    @Id
    @Column(name = "cid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @ManyToOne
    @JoinColumn(name = "uid",nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "pid",nullable = false)
    private ProductEntity product;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "isProccessed",nullable = false, columnDefinition = "boolean default false")
    private Boolean isProccessed = false;

    public CartItemEntity() {
    }

    public CartItemEntity(ProductEntity product, UserEntity user, Integer quantity) {
        setUser(user);
        setProduct(product);
        setQuantity(quantity);
        setProccessed(false);
    }

    public boolean isProccessed() {
        return isProccessed;
    }

    public void setProccessed(boolean proccessed) {
        isProccessed = proccessed;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
