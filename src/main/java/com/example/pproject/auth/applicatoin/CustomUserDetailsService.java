package com.example.pproject.auth.applicatoin;

import com.example.pproject.account.domain.Account;
import com.example.pproject.account.domain.AccountAdapter;
import com.example.pproject.account.domain.AccountRepository;
import com.example.pproject.account.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByEmail(email);

        if(account.equals(Optional.empty())) {
            throw new UsernameNotFoundException("user not found");
        }

        return new AccountAdapter(account.get());
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Account account) {
        return User.builder()
                .username(account.getEmail())
                .password(account.getPassword()) //이미 account password는 암호화 되어있어서 그냥 password만 받음
                .roles(account.getRole())
                .build();
    }
}
