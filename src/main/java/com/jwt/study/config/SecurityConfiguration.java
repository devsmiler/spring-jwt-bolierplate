package com.jwt.study.config;

import com.jwt.study.exception.CustomAccessDeniedHandler;
import com.jwt.study.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity @EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()// Cross site
                .headers().frameOptions().disable().and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/hello","/member/sign-up","/member/sign-in").permitAll() // 얘네는 인증 필요없엉 허용하겠다!!!
                .anyRequest().authenticated()// 나머지 요청은 인증 필요
                .and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())// 403 에러 커스텀 
                .and().exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())// 401 에러 커스텀
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
