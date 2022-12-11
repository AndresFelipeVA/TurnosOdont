package com.andresFVA.turnosOdont.repositories;

import com.andresFVA.turnosOdont.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
