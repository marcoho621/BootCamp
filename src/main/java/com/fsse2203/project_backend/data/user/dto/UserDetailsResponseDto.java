package com.fsse2203.project_backend.data.user.dto;

import com.fsse2203.project_backend.data.user.UserDetailsData;

public class UserDetailsResponseDto {
    private Integer uid;
    private String firebaseUid;
    private String email;

    public UserDetailsResponseDto(UserDetailsData data) {
        setUid(data.getUid());
        setFirebaseUid(data.getFirebaseUid());
        setEmail(data.getEmail());
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
