package com.example.pproject.account.domain;

import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.auth.domain.MyAuthority;
import com.example.pproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Entity
@Getter
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    Long memberId;

    public Account() {

    }


    public Account(String email, String password, Long memberId) {
        this.email = email;
        this.password = password;
        this.memberId = memberId;
    }

    public Optional<AccountDto> convertToDto(){
        return Optional.of(new AccountDto(this.email, this.password, this.memberId));
    }
}
