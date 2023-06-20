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
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    public TokenResponse signIn(SignIn signIn) {
        return getTokenResponse(signIn, memberRepository, jwtTokenProvider);
    }

    static TokenResponse getTokenResponse(SignIn signIn, MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
        Member member = memberRepository.findByEmail(signIn.getEmail()).orElseThrow(RuntimeException::new); // 에러 핸들링 필요
        if (member.getPassword().equals(signIn.getPassword())){
            return TokenResponse.builder().token(jwtTokenProvider.createToken(String.valueOf(member.getId()), member.getRoles())).build();
        }
        return TokenResponse.builder().token("토큰 인증 실패했어율 에러 처리는 나중에하겠수다..").build();
    }


    @Transactional
    public String signUp(SignUp signUp) {
        log.info(signUp.toString());
        Member save = memberRepository.save(signUp.toMemberEntity());
        return save.getEmail();

    }

}
