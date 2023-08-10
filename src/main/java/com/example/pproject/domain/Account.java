package com.example.pproject.domain;

import lombok.Data;

@Data
public class Account {
    String id;
    String password;

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
