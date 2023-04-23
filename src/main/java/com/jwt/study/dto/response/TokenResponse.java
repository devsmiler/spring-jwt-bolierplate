package com.jwt.study.dto.response;

import lombok.Builder;

public class SignIn {
    private String token;

    @Builder
    public SignIn(String token) {
        this.token = token;
    }
}
