package com.fsse2203.project_backend.api;

import com.fsse2203.project_backend.config.EnvConfig;
import com.fsse2203.project_backend.data.product.ProductDetailData;
import com.fsse2203.project_backend.data.product.dto.CreateProductRequestDto;
import com.fsse2203.project_backend.data.product.dto.CreateProductResponseDto;
import com.fsse2203.project_backend.data.product.dto.GetAllProductResponseDto;
import com.fsse2203.project_backend.data.product.dto.ProductDetailResponseDto;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.exception.InvalidProductException;
import com.fsse2203.project_backend.exception.ProductNotFoundException;
import com.fsse2203.project_backend.exception.UserNotFoundException;
import com.fsse2203.project_backend.service.ProductService;
import com.fsse2203.project_backend.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl}, maxAge = 3600)
@RestController
public class ProductApi {
    private final ProductService productService;
    @Autowired
    public ProductApi(ProductService productService) {
        this.productService = productService;
    }
    private SecurityUtil secutity = new SecurityUtil();
    Logger logger = LoggerFactory.getLogger(ProductApi.class);

    @PostMapping("/product/create")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto product, Authentication authentication) throws InvalidProductException, UserNotFoundException {
        UserEntity user = productService.loginRequired(secutity.getFirebaseUid(authentication));
        if(user == null) {
            logger.warn("user not found.");
            throw new UserNotFoundException();
        }
        if(user.getUid() != 1){
            logger.warn("user not permission.");
            throw new UserNotFoundException();
        }
        return new CreateProductResponseDto(productService.createProduct(new ProductDetailData(product)));
    }

    @GetMapping("/public/product")
    public List<GetAllProductResponseDto> getAllProduct() throws ProductNotFoundException {
        List<GetAllProductResponseDto> productList = new ArrayList<>();
        for (ProductDetailData productData : productService.getAllProduct()) {
            productList.add(new GetAllProductResponseDto(productData));
        }
        return productList;
    }

    @GetMapping ("/public/product/{id}")
    public ProductDetailResponseDto getProductByPid(@PathVariable Integer id) throws ProductNotFoundException {
        return new ProductDetailResponseDto(new ProductDetailData(productService.getProductByPid(id)));
    }

    @GetMapping("/public/product/nameAsc")
    public List<GetAllProductResponseDto> getAllProductByProductNameAsc() throws ProductNotFoundException {
        List<GetAllProductResponseDto> productList = new ArrayList<>();
        for (ProductDetailData productData : productService.getAllProductByProductNameAsc()) {
            productList.add(new GetAllProductResponseDto(productData));
        }
        return productList;
    }

    @GetMapping("/public/product/nameDesc")
    public List<GetAllProductResponseDto> getAllProductByProductNameDesc() throws ProductNotFoundException {
        List<GetAllProductResponseDto> productList = new ArrayList<>();
        for (ProductDetailData productData : productService.getAllProductByProductNameDesc()) {
            productList.add(new GetAllProductResponseDto(productData));
        }
        return productList;
    }

    @GetMapping("/public/product/priceAsc")
    public List<GetAllProductResponseDto> getAllProductByProductPriceAsc() throws ProductNotFoundException {
        List<GetAllProductResponseDto> productList = new ArrayList<>();
        for (ProductDetailData productData : productService.getAllProductByProductPriceAsc()) {
            productList.add(new GetAllProductResponseDto(productData));
        }
        return productList;
    }

    @GetMapping("/public/product/priceDesc")
    public List<GetAllProductResponseDto> getAllProductByProductPriceDesc() throws ProductNotFoundException {
        List<GetAllProductResponseDto> productList = new ArrayList<>();
        for (ProductDetailData productData : productService.getAllProductByProductPriceDesc()) {
            productList.add(new GetAllProductResponseDto(productData));
        }
        return productList;
    }
}
