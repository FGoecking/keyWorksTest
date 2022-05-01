package com.keyworksteste.keyworks.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DuplicatedObjectException extends RuntimeException{

    private String errorCode;
    private String errorMessage;
}