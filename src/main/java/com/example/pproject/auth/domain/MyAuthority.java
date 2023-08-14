package com.example.pproject.auth.domain;

import org.springframework.security.core.GrantedAuthority;

//커스텀 클래스
public class MyAuthority implements GrantedAuthority {
    private String authority;

    public MyAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
