package com.keyworksteste.keyworks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicatedObjectException.class)
    public ResponseEntity<String> duplicatedObjectException(DuplicatedObjectException duplicatedObjectException){

        return new ResponseEntity<>("Object duplicated", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundRecordException.class)
    public ResponseEntity<String> notFoundRecordException(NotFoundRecordException notFoundRecordException){

        return new ResponseEntity<>("Not found record", HttpStatus.NOT_FOUND);
    }
}
