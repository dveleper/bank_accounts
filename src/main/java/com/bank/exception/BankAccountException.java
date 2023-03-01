package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class BankAccountException extends RuntimeException {
    public BankAccountException(String message) {
        super(message);
    }
}
