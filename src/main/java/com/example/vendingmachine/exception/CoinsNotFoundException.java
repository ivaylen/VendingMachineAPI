package com.example.vendingmachine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class CoinsNotFoundException extends RuntimeException {
    public CoinsNotFoundException(String message){
        super(message);
    }
}
