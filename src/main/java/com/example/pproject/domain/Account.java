package com.example.pproject.domain;

import lombok.Data;

@Data
public class Account {
    Long accountId;
    String email;
    String password;
    Long memberId;

    public Account(String email, String password, Long memberId) {
        this.email = email;
        this.password = password;
        this.memberId = memberId;
    }

    public Account(Long accountId, String email, String password, Long memberId) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.memberId = memberId;
    }
}
