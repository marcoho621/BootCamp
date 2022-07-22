package com.fsse2203.project_backend.repository;

import com.fsse2203.project_backend.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    @Query(value = "select * from user u where u.firebase_uid = ?1",nativeQuery = true)
    UserEntity findUserEntityByFirebaseUid(@Param("firebaseUid")String firebaseUid);
}
