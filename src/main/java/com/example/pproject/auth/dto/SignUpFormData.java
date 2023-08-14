package com.example.pproject.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpFormData {
    @NotEmpty(message = "please enter your first name")
    private String firstName;

    @NotEmpty(message = "please enter your last name")
    private String lastName;

    @Email
    @NotEmpty(message = "please enter your email")
    private String email;

    @NotEmpty(message = "please enter your password")
    private String password;

    //private String passwordConfirmation;
}
