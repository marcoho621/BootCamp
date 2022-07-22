package com.fsse2203.project_backend.repository;

import com.fsse2203.project_backend.data.cart.entity.CartItemEntity;
import com.fsse2203.project_backend.data.product.entity.ProductEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity,Integer> {
    @Query(value = "select * from cart_item c where c.uid = ?1",nativeQuery = true)
    List<CartItemEntity> findByUser(@Param("user")UserEntity user);
    @Query(value = "select * from cart_item c where c.uid = ?1 AND c.pid = ?2",nativeQuery = true)
    CartItemEntity findByUserAndProduct(@Param("user")UserEntity user, @Param("product")ProductEntity product);
}