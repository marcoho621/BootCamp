package com.fsse2203.project_backend.data.cart;

import com.fsse2203.project_backend.data.cart.entity.CartItemEntity;
import com.fsse2203.project_backend.data.product.ProductDetailData;
import com.fsse2203.project_backend.data.product.entity.ProductEntity;

public class CartItemDetailsData {
    private ProductDetailData product;
    private Integer uid;
    private Integer quantity;
    private boolean isProccessed;

    public CartItemDetailsData(CartItemEntity creatCart) {
        setProduct(creatCart.getProduct());
        setUid(creatCart.getUser().getUid());
        setQuantity(creatCart.getQuantity());
        setProccessed(creatCart.isProccessed());
    }

    public boolean isProccessed() {
        return isProccessed;
    }

    public void setProccessed(boolean proccessed) {
        isProccessed = proccessed;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDetailData getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = new ProductDetailData(product);
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
