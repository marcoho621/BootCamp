package com.fsse2203.project_backend.service;

import com.fsse2203.project_backend.data.product.ProductDetailData;
import com.fsse2203.project_backend.data.product.entity.ProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.InvalidProductException;
import com.fsse2203.project_backend.exception.InvalidProductQuantityException;
import com.fsse2203.project_backend.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    UserEntity loginRequired(String firebaseUid);
    ProductDetailData createProduct(ProductDetailData data)throws InvalidProductException;
    List<ProductDetailData>getAllProduct()throws ProductNotFoundException;
    ProductEntity getProductByPid(Integer pid)throws ProductNotFoundException;
    ProductEntity deduceProductStock(Integer pid, Integer quantity) throws ProductNotFoundException, InvalidProductQuantityException;
    List<ProductDetailData>getAllProductByProductNameAsc()throws ProductNotFoundException;
    List<ProductDetailData>getAllProductByProductNameDesc()throws ProductNotFoundException;
    List<ProductDetailData>getAllProductByProductPriceAsc()throws ProductNotFoundException;
    List<ProductDetailData>getAllProductByProductPriceDesc()throws ProductNotFoundException;
}
