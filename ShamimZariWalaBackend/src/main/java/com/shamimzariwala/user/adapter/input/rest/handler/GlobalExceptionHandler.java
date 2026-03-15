package com.shamimzariwala.user.adapter.input.rest.handler;

import com.shamimzariwala.user.domain.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
     //ProblemDetails: RFC 7807 - This is an internet standard that defines exactly how an HTTP error should look
    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, 
                ex.getMessage()
        );
        problemDetail.setTitle("User Not Found");
        return problemDetail;
    }
}
