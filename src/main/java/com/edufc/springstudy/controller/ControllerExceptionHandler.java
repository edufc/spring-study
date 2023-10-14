package com.edufc.springstudy.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ControllerResponse> handlerException(Exception ex) {
        var httpError = HttpStatus.INTERNAL_SERVER_ERROR;

        ControllerResponse response = new ControllerResponse(httpError.value(), "server error", LocalDateTime.now());

        ex.printStackTrace();

        return new ResponseEntity<>(response, null, httpError);
    }

    @ExceptionHandler
    public ResponseEntity<ControllerResponse> handlerException(MethodArgumentTypeMismatchException ex) {
        var httpError = HttpStatus.BAD_REQUEST;

        ControllerResponse response = new ControllerResponse(httpError.value(), "invalid argument", LocalDateTime.now());

        ex.printStackTrace();

        return new ResponseEntity<>(response, null, httpError);
    }
}