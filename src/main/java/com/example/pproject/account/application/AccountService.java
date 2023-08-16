package com.example.pproject.account.application;

import com.example.pproject.account.domain.AccountRepository;
import com.example.pproject.auth.applicatoin.JwtTokenProvider;
import com.example.pproject.auth.domain.TokenInfo;
import com.example.pproject.account.domain.Account;
import com.example.pproject.account.dto.AccountDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
//    public Account save(Account account) {
//        return accountRepository.save(account);
//    }



    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> findAllMembers() {
        return accountRepository.findAll();
    }


    //실패시 Optional.empty()반환
    public Optional<Account> createAccount(AccountDto accountDto) {
        final String email = accountDto.getEmail();

        Optional<Account> foundEmail = accountRepository.findByEmail(email);

        if(!foundEmail.isEmpty())
            return Optional.empty();
        else{
            accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));

            Account account = new Account(
                    accountDto.getEmail(),
                    accountDto.getPassword(),
                    accountDto.getMemberId(),
                    "USER"
            );

            //암호화
            accountRepository.save(account);

            return Optional.of(account);
        }
    }

    public TokenInfo login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 falseE
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        return tokenInfo;
    }
}
