package com.andresFVA.turnosOdont.services;

import com.andresFVA.turnosOdont.models.Patient;

import java.util.List;

public interface PatientService {
    Patient findPatient(Long dni);

    List<Patient> findAll();

    Patient create(Patient patient);

    void deletePatient(Long dni);
}
