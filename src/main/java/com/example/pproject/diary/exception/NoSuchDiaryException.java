package com.example.pproject.diary.exception;

public class NoSuchDiaryException extends RuntimeException {
    public NoSuchDiaryException(final String message) {
        super(message);
    }

    public NoSuchDiaryException() {
        this("존재하지 않는 회원입니다.");
    }
}
