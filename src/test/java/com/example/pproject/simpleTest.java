package com.example.pproject;

import com.example.pproject.account.domain.AccountAdapter;
import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.auth.applicatoin.JwtTokenProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class simpleTest {

    @Test
    void test1(){
        Assertions.assertThat(UserDetails.class).isAssignableFrom(User.class);
        Assertions.assertThat(AccountAdapter.class).isAssignableFrom(User.class);
        AuthenticationProvider.class.isAssignableFrom(JwtTokenProvider.class);
    }
}