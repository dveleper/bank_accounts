package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class BankAccountCuentaException extends RuntimeException {
    public BankAccountCuentaException(String message) {
        super(message);
    }
}
