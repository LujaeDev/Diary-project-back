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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        Account createdAccount = optional.get();

        log.info("createdAccount = {}", createdAccount);
        return signInProcess(new SignInFormData(formData.getEmail(), formData.getPassword()));
    }


    @PostMapping("/api/signIn")
    public Response<?> signIn(@RequestBody SignInFormData formData) {
        log.info("inner sign in: {}", formData);
        return signInProcess(formData);
    }

    @GetMapping("/api/main")
    public void mainPage(@RequestHeader("Authorization") String auth) {
        log.info("{}", auth);
    }

    private Optional<Account> processSignUp(SignUpFormData formData){
        log.info("processSignUp start");
        Member member = new Member(formData);
        memberService.save(member);

        AccountDto accountDto = new AccountDto(formData);
        accountDto.setMemberId(member.getMemberId());

        Optional<Account> createdAccount = accountService.createAccount(accountDto);
        log.info("processSignUp end");


        return createdAccount;
    }

    private Response<?> signInProcess(SignInFormData formData){
        TokenInfo tokenInfo = null;
        boolean success = false;
        String successOrFail = "Success";

        try{
            tokenInfo = accountService.login(formData.getEmail(), formData.getPassword());
            success = true;
        } catch(Exception e){
            log.info("exception = {}", e.toString());
            successOrFail = "Fail";
        } finally {
            return new Response<>(String.valueOf(success),  successOrFail + " to sign in", tokenInfo);
        }
    }
}
