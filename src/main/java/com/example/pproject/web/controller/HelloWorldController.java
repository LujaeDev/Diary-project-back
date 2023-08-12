package com.example.pproject.web.controller;

import com.example.pproject.domain.Account;
import com.example.pproject.domain.Member;
import com.example.pproject.repository.AccountDto;
import com.example.pproject.repository.memberRepo.MemberRepository;
import com.example.pproject.service.AccountService;
import com.example.pproject.service.MemberService;
import com.example.pproject.web.form.SignUpFormData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloWorldController {
    private final MemberService memberService;
    private final AccountService accountService;

    @GetMapping("/api/hello")
    public String test() {
        return "Hello1111";
    }

    @PostMapping("api/signUp")
    public Optional<Account> signUp(@RequestBody SignUpFormData formData){
        log.info("{}", formData);

        Optional<Account> optional = processSignUp(formData);

        log.info("optional = {}", optional);
        return optional;
    }

    private Optional<Account> processSignUp(SignUpFormData formData){
        Member member = new Member(formData);
        memberService.save(member);

        AccountDto accountDto = new AccountDto(formData);
        accountDto.setMemberId(member.getMemberId());

        Optional<Account> createdAccount = accountService.createAccount(accountDto);

        return createdAccount;
    }
}
