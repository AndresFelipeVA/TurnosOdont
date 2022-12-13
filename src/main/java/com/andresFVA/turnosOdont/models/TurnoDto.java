package com.andresFVA.turnosOdont.models;

import com.andresFVA.turnosOdont.models.Utils.Person;

import java.time.LocalDateTime;
import java.time.Period;

public class TurnoDto {
    private Long id;
    private Person dentist;
    private Person patient;
    private LocalDateTime fecha_Hora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getDentist() {
        return dentist;
    }

    public void setDentist(Person dentist) {
        this.dentist = dentist;
    }

    public Person getPatient() {
        return patient;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public LocalDateTime getFecha_Hora() {
        return fecha_Hora;
    }

    public void setFecha_Hora(LocalDateTime fecha_Hora) {
        this.fecha_Hora = fecha_Hora;
    }
}
