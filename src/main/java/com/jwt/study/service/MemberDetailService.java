package com.jwt.study.service;

import com.jwt.study.config.JwtTokenProvider;
import com.jwt.study.domain.Member;
import com.jwt.study.dto.request.SignIn;
import com.jwt.study.dto.request.SignUp;
import com.jwt.study.dto.response.TokenResponse;
import com.jwt.study.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service @Slf4j
public class MemberDetailService {
    private final MemberRepository memberRepository;

    public Member getMemberId(String userId) {
        return memberRepository.findById(Long.valueOf(userId)).orElseThrow(RuntimeException::new);
    }

}
