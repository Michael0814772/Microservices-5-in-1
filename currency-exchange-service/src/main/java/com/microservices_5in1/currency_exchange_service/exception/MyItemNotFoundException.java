package com.microservices_5in1.currency_exchange_service.exception;

public class MyItemNotFoundException extends RuntimeException{

    public MyItemNotFoundException(String message) {
        super(message);
    }
}
