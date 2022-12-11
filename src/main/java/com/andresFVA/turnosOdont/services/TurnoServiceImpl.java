package com.andresFVA.turnosOdont.services;

import com.andresFVA.turnosOdont.models.Turno;
import com.andresFVA.turnosOdont.repositories.DentistRepository;
import com.andresFVA.turnosOdont.repositories.PatientRepository;
import com.andresFVA.turnosOdont.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService{

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno findTurno(Long id) {
        return turnoRepository.getReferenceById(id);
    }

    @Override
    public Turno create(Turno turno) {
        /*En el momento no se verifica si ya existe
         * Si no existe lo crea, si existe lo actualiza*/
        //TODO: Validar - El paciente debe existir
        //TODO: Validar - El odontologo debe existir
        //TODO: Validar - Fecha
        return turnoRepository.saveAndFlush(turno);
    }

    @Override
    public void deleteTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}
