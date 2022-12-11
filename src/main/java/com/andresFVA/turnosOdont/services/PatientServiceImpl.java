package com.andresFVA.turnosOdont.services;

import com.andresFVA.turnosOdont.models.Patient;
import com.andresFVA.turnosOdont.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient create(Patient patient) {
        /*En el momento no se verifica si ya existe
        * Si no existe lo crea, si existe lo actualiza*/
        return patientRepository.saveAndFlush(patient);
    }

    @Override
    public void deletePatient(Long dni) {
        patientRepository.deleteById(dni);
    }


    @Override
    public Patient findPatient(Long dni) {
        return patientRepository.getReferenceById(dni);
    }
}
