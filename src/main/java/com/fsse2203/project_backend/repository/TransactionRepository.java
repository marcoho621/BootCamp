package com.fsse2203.project_backend.repository;

import com.fsse2203.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity,Integer> {
    @Query(value = "select * from transaction t where t.tid = ?1",nativeQuery = true)
    TransactionEntity findByTid(@Param("tid") Integer tid);
}
