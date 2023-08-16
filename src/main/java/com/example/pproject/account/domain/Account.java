package com.example.pproject.account.domain;

import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.auth.domain.MyAuthority;
import com.example.pproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Entity
@Getter
public class Account extends BaseEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    Long memberId;

    @Column(nullable = false)
    private String role;

    public Account() {

    }

    public Account(String email, String password, Long memberId, String role) {
        this.email = email;
        this.password = password;
        this.memberId = memberId;
        this.role = role;
    }

    public Optional<AccountDto> convertToDto(){
        return Optional.of(new AccountDto(this.email, this.password, this.memberId));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for(String role : role.split(",")){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
