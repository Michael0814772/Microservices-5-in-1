package com.microservices_5in1.currency_exchange_service.exception;

import com.microservices_5in1.currency_exchange_service.exception.dto.GeneralException;
import com.microservices_5in1.currency_exchange_service.exception.dto.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(MyItemNotFoundException.class)
    public ResponseEntity<ItemNotFoundException> handleMyItemNotFoundException(MyItemNotFoundException ex) {

        ItemNotFoundException itemNotFoundException = new ItemNotFoundException();
        itemNotFoundException.setResponseMsg(ex.getMessage());
        itemNotFoundException.setResponseCode("99");
        log.error("User not found: {}", itemNotFoundException);
        return new ResponseEntity<>(itemNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ItemNotFoundException> handleException(Exception ex) {

        ItemNotFoundException usserNotFound = new ItemNotFoundException();
        usserNotFound.setResponseMsg(ex.getMessage());
        usserNotFound.setResponseCode("99");
        log.error("INTERNAL_SERVER_ERROR: {}", usserNotFound);
        return new ResponseEntity<>(usserNotFound, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralException> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        GeneralException generalException = new GeneralException();
        generalException.setResponseMsg(errors.toString());

        log.info("handleMethodArgumentNotValidException - {}", generalException);
        return new ResponseEntity<>(generalException, HttpStatus.BAD_REQUEST);
    }
}
