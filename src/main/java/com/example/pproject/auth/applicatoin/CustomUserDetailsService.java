package com.example.pproject.auth.applicatoin;

import com.example.pproject.account.domain.Account;
import com.example.pproject.account.domain.AccountRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepositoryV1 accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Account account) {
        return User.builder()
                .username(account.getEmail())
                .password(account.getPassword()) //이미 account password는 암호화 되어있어서 그냥 password만 받음
                .roles(account.getRoles().toArray(new String[0]))
                .build();
    }
}
