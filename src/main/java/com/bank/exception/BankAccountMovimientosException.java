package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class BankAccountMovimientosException extends RuntimeException {
    public BankAccountMovimientosException(String message) {
        super(message);
    }
}
