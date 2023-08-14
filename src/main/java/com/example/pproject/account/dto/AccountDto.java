package com.example.pproject.account.dto;

import com.example.pproject.auth.domain.MyAuthority;
import com.example.pproject.auth.dto.SignUpFormData;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountDto implements UserDetails{
    String email;
    String password;
    Long memberId;

    public AccountDto(String email, String password, Long memberId) {
        this.email = email;
        this.password = password;
        this.memberId = memberId;
    }

    public AccountDto(SignUpFormData formData) {
        this.email = formData.getEmail();
        this.password = formData.getPassword();
    }

    private List<String> roles = new ArrayList<>();


    public void addRole(String role){
        if(!roles.contains(role))
            roles.add(role);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(MyAuthority::new)
                .collect(Collectors.toList());
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
