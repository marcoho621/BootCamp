package com.fsse2203.project_backend.data.product.dto;

import com.fsse2203.project_backend.data.product.ProductDetailData;

import java.math.BigDecimal;

public class ProductDetailResponseDto {
    private Integer pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;

    public ProductDetailResponseDto(ProductDetailData data) {
        setPid(data.getPid());
        setName(data.getName());
        setImageUrl(data.getImageUrl());
        setPrice(data.getPrice());
        setDescription(data.getDescription());
        setStock(data.getStock());
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
}
