package com.fsse2203.project_backend.repository;

import com.fsse2203.project_backend.data.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {
    @Query(value = "select * from product p where p.pid = ?1",nativeQuery = true)
    ProductEntity findProductEntityByPid(Integer pid);
    boolean existsByName(@Param("name")String name);
    @Query(value = "select * from product p ORDER BY name",nativeQuery = true)
    List<ProductEntity> findAllProductEntityByNameAsc();
    @Query(value = "select * from product p ORDER BY name DESC",nativeQuery = true)
    List<ProductEntity> findAllProductEntityByNameDesc();
    @Query(value = "select * from product p ORDER BY price",nativeQuery = true)
    List<ProductEntity> findAllProductEntityByPriceAsc();
    @Query(value = "select * from product p ORDER BY price DESC",nativeQuery = true)
    List<ProductEntity> findAllProductEntityByPriceDesc();
}
