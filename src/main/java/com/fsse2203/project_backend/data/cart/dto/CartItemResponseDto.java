package com.fsse2203.project_backend.data.cart.dto;

import com.fsse2203.project_backend.data.cart.CartItemData;

public class CartItemResponseDto {
    private String result;

    public CartItemResponseDto(CartItemData data) {
        setResult(data.getResult());
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
