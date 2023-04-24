package com.jwt.study.controller;

import com.jwt.study.dto.request.SignIn;
import com.jwt.study.dto.request.SignUp;
import com.jwt.study.dto.response.TokenResponse;
import com.jwt.study.service.AuthService;
import com.jwt.study.service.MemberDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    @PostMapping("member/sign-in")
    public TokenResponse signIn(@RequestBody SignIn signIn) {
        return authService.signIn(signIn);
    }

    @PostMapping("member/sign-up")
    public String signIn(@RequestBody SignUp signUp){
        return authService.signUp(signUp);
    }
}
