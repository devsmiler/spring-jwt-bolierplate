package com.jwt.study.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity @AllArgsConstructor @Data
@Slf4j
public class Member {
    @GeneratedValue @Id
    Long id;
    String email;
    String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();
    public Member() {

    }

    @Builder
    public Member(String email, String password, List<String> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> collect = this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        log.info("Collect :" + collect.toString());
        log.info("my roles :" + this.roles.toString());
        return  collect;
    }
}
