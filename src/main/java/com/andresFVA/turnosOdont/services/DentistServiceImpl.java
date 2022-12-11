package com.andresFVA.turnosOdont.services;

import com.andresFVA.turnosOdont.models.Dentist;
import com.andresFVA.turnosOdont.repositories.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DentistServiceImpl implements DentistService {

    @Autowired
    private DentistRepository dentistRepository;
    @Override
    public List<Dentist> findAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist findDentist(Long matricula) {
        return dentistRepository.getReferenceById(matricula);
    }

    @Override
    public Dentist create(Dentist dentist) {
        /*En el momento no se verifica si ya existe
         * Si no existe lo crea, si existe lo actualiza*/
        return dentistRepository.saveAndFlush(dentist);
    }

    @Override
    public void deleteDentist(Long matricula) {
        dentistRepository.deleteById(matricula);
    }
}
