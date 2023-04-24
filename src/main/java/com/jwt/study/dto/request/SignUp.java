package com.jwt.study.dto.request;

import com.jwt.study.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@Getter
@Setter
public class SignUp {
    String email;
    String password; // 나중에 인크립트 ㄱ ㄱ

    public Member toMemberEntity(){
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .roles(Collections.singletonList("GUEST"))
                .build();
    }
}
