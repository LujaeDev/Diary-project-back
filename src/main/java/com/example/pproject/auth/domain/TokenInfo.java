package com.example.pproject.auth.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenInfo {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
