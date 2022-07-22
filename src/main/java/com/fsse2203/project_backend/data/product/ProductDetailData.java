package com.fsse2203.project_backend.data.product;

import com.fsse2203.project_backend.data.product.dto.CreateProductRequestDto;
import com.fsse2203.project_backend.data.product.entity.ProductEntity;
import com.fsse2203.project_backend.data.transactionProduct.entity.TransactionProductEntity;

import java.math.BigDecimal;

public class ProductDetailData {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private String description;
    private Integer stock;

    public ProductDetailData(CreateProductRequestDto data) {
        setName(data.getName());
        setImageUrl(data.getImageUrl());
        setPrice(data.getPrice());
        setDescription(data.getDescription());
        setStock(data.getStock());
    }

    public ProductDetailData(TransactionProductEntity entity) {
        setPid(entity.getPid());
        setName(entity.getName());
        setImageUrl(entity.getImageUrl());
        setPrice(entity.getPrice());
        setDescription(entity.getDescription());
        setStock(entity.getStock());
    }

    public ProductDetailData(ProductEntity entity) {
        setPid(entity.getPid());
        setName(entity.getName());
        setImageUrl(entity.getImageUrl());
        setPrice(entity.getPrice());
        setDescription(entity.getDescription());
        setStock(entity.getStock());
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
