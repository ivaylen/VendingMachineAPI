package com.example.vendingmachine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class ProductIdAlreadyExistsException extends RuntimeException{
    public ProductIdAlreadyExistsException(String message){
        super(message);
    }
}
