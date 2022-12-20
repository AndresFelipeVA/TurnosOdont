package com.andresFVA.turnosOdont.exceptions.utils;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

public class KnownException {

    public static String getMensaje(RuntimeException ex){
        String mensaje=ex.toString();
        if(ex.getCause() instanceof ConstraintViolationException){
            mensaje = "No se puede eliminar mientras tenga un turno asignado";
        } else if (ex.getCause() instanceof PropertyValueException) {
            mensaje = "Debe seleccionar un Odontólogo y paciente existentes";
        }
        return mensaje;
    }
}
