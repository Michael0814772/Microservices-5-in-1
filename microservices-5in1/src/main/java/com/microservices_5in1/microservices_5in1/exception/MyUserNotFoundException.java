package com.microservices_5in1.microservices_5in1.exception;

public class MyUserNotFoundException extends RuntimeException{

    public MyUserNotFoundException(String message) {
        super(message);
    }
}
