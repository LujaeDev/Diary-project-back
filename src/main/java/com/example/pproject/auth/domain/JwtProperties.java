package com.example.pproject.auth.domain;

public interface JwtProperties {
    // 서버만 알고있는 시크릿키
    String SECRET = "sesecret";
    int EXPIRATION_TIME = 60000 * 60;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

    //리프레쉬 키 필요, 토큰 시간도 짧게
}
