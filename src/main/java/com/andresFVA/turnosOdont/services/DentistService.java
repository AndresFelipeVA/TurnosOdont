package com.andresFVA.turnosOdont.services;

import com.andresFVA.turnosOdont.models.Dentist;

import java.util.List;

public interface DentistService {
    List<Dentist> findAll();

    Dentist findDentist(Long matricula);

    Dentist create(Dentist dentist);

    void deleteDentist(Long matricula);
}
