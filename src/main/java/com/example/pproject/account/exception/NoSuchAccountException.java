package com.example.pproject.account.exception;

public class NoSuchAccountException extends RuntimeException{
    public NoSuchAccountException(final String message) {
        super(message);
    }

    public NoSuchAccountException() {
        this("존재하지 않는 회원입니다.");
    }
}
