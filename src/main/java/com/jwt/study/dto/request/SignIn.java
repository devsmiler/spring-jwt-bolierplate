package com.jwt.study.dto.request;

import com.jwt.study.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@Getter
@Setter
public class SignIn {
    String email;
    String password; // 나중에 인크립트 ㄱ ㄱ

}
