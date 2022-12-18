package com.andresFVA.turnosOdont.controllers;

import com.andresFVA.turnosOdont.models.Patient;
import com.andresFVA.turnosOdont.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getPatients(){
        return patientService.findAll();
    }

    @GetMapping("/{dni}")
    @ResponseStatus(HttpStatus.OK)//No necesario es el valor por defecto
    public Patient getPatient(@PathVariable Long dni){
        return patientService.findPatient(dni);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(Patient patient){
        return patientService.create(patient);
    }

    @DeleteMapping("/{dni}")
    public void deletePatient(@PathVariable Long dni){
        patientService.deletePatient(dni);
    }

}
