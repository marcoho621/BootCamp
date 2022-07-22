package com.fsse2203.project_backend.data.transaction;

import com.fsse2203.project_backend.data.product.ProductDetailData;
import com.fsse2203.project_backend.data.transactionProduct.entity.TransactionProductEntity;

import java.math.BigDecimal;

public class TransactionProductDetailData {
    private Integer tpid;
    private ProductDetailData product;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransactionProductDetailData(TransactionProductEntity entity) {
        setTpid(entity.getTpid());
        setProduct(entity);
        setQuantity(entity.getQuantity());
        setSubtotal(entity.getSubtotal());
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductDetailData getProduct() {
        return product;
    }

    public void setProduct(TransactionProductEntity entity) {
        this.product = new ProductDetailData(entity);
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

    @Override
    public String toString() {
        return "TransactionProductDetailData{" +
                "tpid=" + tpid +
                ", product=" + product +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
