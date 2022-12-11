package com.andresFVA.turnosOdont.services;

import com.andresFVA.turnosOdont.models.Turno;

import java.util.List;

public interface TurnoService {
    List<Turno> findAll();

    Turno findTurno(Long matricula);

    Turno create(Turno turno);

    void deleteTurno(Long matricula);
}
