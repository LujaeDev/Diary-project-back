package com.example.pproject.global.controller;

import com.example.pproject.auth.domain.TokenInfo;
import com.example.pproject.account.domain.Account;
import com.example.pproject.member.domain.Member;
import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.account.application.AccountService;
import com.example.pproject.member.application.MemberService;
import com.example.pproject.auth.dto.SignInFormData;
import com.example.pproject.auth.dto.SignUpFormData;
import com.example.pproject.global.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
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
    public Response<?> signUp(@Valid @RequestBody SignUpFormData formData, BindingResult bindingResult) {
        log.info("{}", formData);

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors() = {}", bindingResult);

            return new Response<>("false", "failed to sign up", null);
        }

        Optional<Account> optional = processSignUp(formData);

        log.info("optional = {}", optional);
        return new Response<>("true", "success to sign up", optional.get());
    }

    @PostMapping("/api/signIn")
    public Response<?> signIn(@RequestBody SignInFormData formData) {
        log.info("start sign in");

        String memberId = formData.getEmail();
        String password = formData.getPassword();


        TokenInfo tokenInfo = accountService.login(memberId, password);
        return new Response<>("true", "success to sign in", tokenInfo);
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
