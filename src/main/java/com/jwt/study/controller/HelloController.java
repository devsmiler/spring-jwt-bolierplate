package com.jwt.study.controller;

import com.jwt.study.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @GetMapping("hello")
    public String hello(){
        return "Hello Kai";
    }
    @PreAuthorize("hasAnyAuthority('GUEST')")
    @GetMapping("hello-secure-guest")
    public String helloSecure(Authentication authentication){
        log.info("auth get name :"+ ((Member) authentication.getPrincipal()) );
        String name = ((Member) authentication.getPrincipal()).getEmail();
        log.info(authentication.getAuthorities().toString());
        return "Hello " + name + ", this is request is secured";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("hello-secure-ADMIN")
    public String helloSecureAdmin(){
        return "Hello Kai this is request is secured";
    }
}
