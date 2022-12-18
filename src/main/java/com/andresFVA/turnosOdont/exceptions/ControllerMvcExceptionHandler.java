package com.andresFVA.turnosOdont.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.andresFVA.turnosOdont.ControllersMVC")
public class ControllerMvcExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleEx(Model model, RuntimeException ex){
        model.addAttribute("error", "ERROR: "+ex.toString());
        return "error";
    }

}
