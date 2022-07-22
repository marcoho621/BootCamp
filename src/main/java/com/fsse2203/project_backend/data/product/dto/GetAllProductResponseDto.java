package com.fsse2203.project_backend.data.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2203.project_backend.data.product.ProductDetailData;

import java.math.BigDecimal;

public class GetAllProductResponseDto {
    @JsonProperty("product_id")
    private Integer pid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("has_stock")
    private boolean hasStock;

    public GetAllProductResponseDto(ProductDetailData data) {
        setPid(data.getPid());
        setName(data.getName());
        setImageUrl(data.getImageUrl());
        setPrice(data.getPrice());
        setHasStock(data.getStock());
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

    public boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Integer stock) {
        this.hasStock = stock > 0;
    }
}
