package com.fsse2203.project_backend.service.impl;

import com.fsse2203.project_backend.data.user.CreateUserData;
import com.fsse2203.project_backend.data.user.UserDetailsData;
import com.fsse2203.project_backend.data.user.entity.UserEntity;
import com.fsse2203.project_backend.repository.UserRepository;
import com.fsse2203.project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsData createUser(CreateUserData data) {
        return new UserDetailsData(userRepository.save(new UserEntity(data)));
    }

    @Override
    public UserDetailsData getUserDetailsByFirebaseUid(String firebaseUid) {
        if(getUserEntityByFirebaseUid(firebaseUid) == null) {
            logger.info("user not found.");
            return null;
        }
        return new UserDetailsData(getUserEntityByFirebaseUid(firebaseUid));
    }

    public UserEntity getUserEntityByFirebaseUid(String firebaseUid){
        return userRepository.findUserEntityByFirebaseUid(firebaseUid);
    }

    public String getFirebaseUidByFirebaseUid(String firebaseUid){
        if(userRepository.findUserEntityByFirebaseUid(firebaseUid) == null) {
            logger.warn("user not found.");
            return null;
        }
        return firebaseUid;
    }
}
