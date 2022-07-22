package com.fsse2203.project_backend.api;

import com.fsse2203.project_backend.config.EnvConfig;
import com.fsse2203.project_backend.service.UserService;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl}, maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserApi {
    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(Authentication authentication){
        FirebaseToken token = (FirebaseToken)authentication.getPrincipal();
        token.getUid();
    }
}
