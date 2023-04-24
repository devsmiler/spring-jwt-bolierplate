package com.jwt.study.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TokenResponse {
    private String token;

    @Builder
    public TokenResponse(String token) {
        this.token = token;
    }
}
