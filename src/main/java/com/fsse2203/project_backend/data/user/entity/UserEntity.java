package com.fsse2203.project_backend.data.user.entity;

import com.fsse2203.project_backend.data.user.CreateUserData;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(name = "email")
    private String email;

    @Column(name = "firebase_uid")
    private String firebaseUid;

    public UserEntity() {
    }

    public UserEntity(CreateUserData data){
        setEmail(data.getEmail());
        setFirebaseUid(data.getFirebaseUid());
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }
}
