package com.fsse2203.project_backend.repository;

import com.fsse2203.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity,Integer> {
}
