package com.example.pproject.account.application;

import com.example.pproject.auth.applicatoin.JwtTokenProvider;
import com.example.pproject.auth.domain.TokenInfo;
import com.example.pproject.account.domain.Account;
import com.example.pproject.auth.dto.AccountDto;
import com.example.pproject.account.domain.AccountRepositoryV1;
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
public class AccountService {
    private final AccountRepositoryV1 accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
//    public Account save(Account account) {
//        return accountRepository.save(account);
//    }

    public void update(Long AccountId, AccountDto updateParam) {
        //업데이트 할 때도 패스워드 암호화 필요 => BCrypt 사용
        accountRepository.update(AccountId, updateParam);
    }

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
            Account account = new Account(
                    accountDto.getEmail(),
                    accountDto.getPassword(),
                    accountDto.getMemberId()
            );

            //암호화
            account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            accountRepository.save(account);

            return Optional.of(account);
        }
    }

    @Transactional
    public TokenInfo login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        return tokenInfo;
    }
}
