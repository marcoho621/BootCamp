package com.fsse2203.project_backend.data.transaction.dto;

import com.fsse2203.project_backend.data.product.ProductDetailData;
import com.fsse2203.project_backend.data.product.dto.ProductDetailResponseDto;
import com.fsse2203.project_backend.data.transaction.TransactionProductDetailData;

import java.math.BigDecimal;

public class TransactionProductResponseDto {
    private Integer tpid;
    private ProductDetailResponseDto product;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransactionProductResponseDto(TransactionProductDetailData data) {
        setTpid(data.getTpid());
        setProduct(data.getProduct());
        setQuantity(data.getQuantity());
        setSubtotal(data.getSubtotal());
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductDetailResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductDetailData product) {
        this.product = new ProductDetailResponseDto(product);
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

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
