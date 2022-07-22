package com.fsse2203.project_backend.data.cart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2203.project_backend.data.cart.CartItemDetailsData;

import java.math.BigDecimal;

public class GetAllCartItemResponseDto {
    @JsonProperty("buyer_id")
    private Integer uid;
    @JsonProperty("product_id")
    private Integer pid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("cart_quantity")
    private Integer quantity;
    @JsonProperty("stock")
    private Integer stock;
    @JsonProperty("isProccess")
    private Boolean isProccess;

    public GetAllCartItemResponseDto(CartItemDetailsData data) {
        setUid(data.getUid());
        setPid(data.getProduct().getPid());
        setName(data.getProduct().getName());
        setImageUrl(data.getProduct().getImageUrl());
        setPrice(data.getProduct().getPrice());
        setQuantity(data.getQuantity());
        setStock(data.getProduct().getStock());
        setProccess(data.isProccessed());
    }

    public Boolean getProccess() {
        return isProccess;
    }

    public void setProccess(Boolean proccess) {
        isProccess = proccess;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
