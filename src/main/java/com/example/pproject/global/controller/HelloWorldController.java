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

        log.info("optional = {}", optional);
        return new Response<>("true", "success to sign up", optional.get());
    }

    private Optional<Account> processSignUp(SignUpFormData formData){
        Member member = new Member(formData);
        memberService.save(member);

        AccountDto accountDto = new AccountDto(formData);
        accountDto.setMemberId(member.getMemberId());

        Optional<Account> createdAccount = accountService.createAccount(accountDto);

        return createdAccount;
    }
    @PostMapping("/api/signIn")
    public Response<?> signIn(@RequestBody SignInFormData formData) {
        String email = formData.getEmail();
        String password = formData.getPassword();

        log.info("inner sign in: {}", formData);

        TokenInfo tokenInfo = null;
        boolean success = false;
        String successOrFail = "Success";

        try{
            tokenInfo = accountService.login(email, password);
            success = true;
        } catch(Exception e){
            log.info("exception = {}", e.toString());
            successOrFail = "Fail";
        } finally {
            return new Response<>(String.valueOf(success),  successOrFail + " to sign in", tokenInfo);
        }
    }

    @GetMapping("/api/main")
    public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("request: ", request);
    }



}
