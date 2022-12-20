package com.andresFVA.turnosOdont.exceptions;

import com.andresFVA.turnosOdont.exceptions.utils.KnownException;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.andresFVA.turnosOdont.ControllersMVC")
public class ControllerMvcExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleEx(Model model, RuntimeException ex){
        model.addAttribute("error", "ERROR: "+ KnownException.getMensaje(ex));
        return "error";
    }

}
