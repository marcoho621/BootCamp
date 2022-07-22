package com.fsse2203.project_backend.service.impl;

import com.fsse2203.project_backend.data.product.ProductDetailData;
import com.fsse2203.project_backend.data.product.entity.ProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.InvalidProductException;
import com.fsse2203.project_backend.exception.InvalidProductQuantityException;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.repository.ProductRepository;
import com.fsse2203.project_backend.service.ProductService;
import com.fsse2203.project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final UserService userService;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public UserEntity loginRequired(String firebaseUid) {
        return userService.getUserEntityByFirebaseUid(firebaseUid);
    }

    @Override
    public ProductDetailData createProduct(ProductDetailData data)throws InvalidProductException {
        if(data.getPrice().intValue() < 0 || data.getStock() <= 0) {
            logger.warn("Invalid price or stock");
            throw new InvalidProductException();
        }
        if(productRepository.existsByName(data.getName())){
            logger.warn("Invalid name");
            throw new InvalidProductException();
        }
        logger.info("create new product");
        return new ProductDetailData(productRepository.save(new ProductEntity(data)));
    }

    @Override
    public List<ProductDetailData> getAllProduct()throws ProductNotFoundException {
        if(((List<ProductEntity>)productRepository.findAll()).isEmpty()) {
            logger.warn("empty product list");
            throw new ProductNotFoundException();
        }
        List<ProductDetailData> productList = new ArrayList<>();
        for(ProductEntity product: productRepository.findAll()){
            productList.add(new ProductDetailData(product));
        }
        return productList;
    }

    @Override
    public ProductEntity getProductByPid(Integer pid)throws ProductNotFoundException{
        if(productRepository.findProductEntityByPid(pid) == null) {
            logger.warn("product id not found");
            throw new ProductNotFoundException();
        }
        return productRepository.findProductEntityByPid(pid);
    }

    @Override
    public ProductEntity deduceProductStock(Integer pid, Integer quantity) throws ProductNotFoundException, InvalidProductQuantityException {
        ProductEntity product = productRepository.findProductEntityByPid(pid);
        if(product == null) {
            logger.warn("product not found.");
            throw new ProductNotFoundException();
        }
        if(product.getStock() < quantity) {
            logger.warn("invalid quantity : stock < quantity.");
            throw new InvalidProductQuantityException();
        }
        product.setStock(product.getStock() - quantity);
        return productRepository.save(product);
    }

    @Override
    public List<ProductDetailData> getAllProductByProductNameAsc() throws ProductNotFoundException {
        if(((List<ProductEntity>)productRepository.findAll()).isEmpty()) {
            logger.warn("empty product list");
            throw new ProductNotFoundException();
        }
        List<ProductDetailData> productList = new ArrayList<>();
        for(ProductEntity product: productRepository.findAllProductEntityByNameAsc()){
            productList.add(new ProductDetailData(product));
        }
        System.out.println(productList);
        return productList;
    }

    @Override
    public List<ProductDetailData> getAllProductByProductNameDesc() throws ProductNotFoundException {
        if(((List<ProductEntity>)productRepository.findAll()).isEmpty()) {
            logger.warn("empty product list");
            throw new ProductNotFoundException();
        }
        List<ProductDetailData> productList = new ArrayList<>();
        for(ProductEntity product: productRepository.findAllProductEntityByNameDesc()){
            productList.add(new ProductDetailData(product));
        }
        System.out.println(productList);
        return productList;
    }

    @Override
    public List<ProductDetailData> getAllProductByProductPriceAsc() throws ProductNotFoundException {
        if(((List<ProductEntity>)productRepository.findAll()).isEmpty()) {
            logger.warn("empty product list");
            throw new ProductNotFoundException();
        }
        List<ProductDetailData> productList = new ArrayList<>();
        for(ProductEntity product: productRepository.findAllProductEntityByPriceAsc()){
            productList.add(new ProductDetailData(product));
        }
        System.out.println(productList);
        return productList;
    }

    @Override
    public List<ProductDetailData> getAllProductByProductPriceDesc() throws ProductNotFoundException {
        if(((List<ProductEntity>)productRepository.findAll()).isEmpty()) {
            logger.warn("empty product list");
            throw new ProductNotFoundException();
        }
        List<ProductDetailData> productList = new ArrayList<>();
        for(ProductEntity product: productRepository.findAllProductEntityByPriceDesc()){
            productList.add(new ProductDetailData(product));
        }
        System.out.println(productList);
        return productList;
    }
}
