package com.example.pproject.auth.dto;

import lombok.Data;

@Data
public class SignInFormData {
    private String email;
    private String password;

    public SignInFormData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
