package com.andresFVA.turnosOdont.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.andresFVA.turnosOdont.controllers")
public class ControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleEx(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

}
