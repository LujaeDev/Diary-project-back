package com.example.pproject.auth.presentation;

import com.example.pproject.auth.Exception.EmptyAuthorizationHeaderException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

public class TokenExtractor {
    private static final String BEARER_TYPE = "Bearer ";

    public static String extract(final HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(authorizationHeader)) {
            throw new EmptyAuthorizationHeaderException();
        }

        return authorizationHeader.substring(BEARER_TYPE.length()).trim();
    }

}
