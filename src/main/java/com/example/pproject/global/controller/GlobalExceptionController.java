package com.example.pproject.global.controller;

import com.example.pproject.account.exception.NoSuchAccountException;
import com.example.pproject.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NoSuchAccountException.class)
    public ResponseEntity<Response> handleCustomException(NoSuchAccountException ex){
        Response response = new Response<>("false", "No Account", null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
