package com.andresFVA.turnosOdont.controllers;

import com.andresFVA.turnosOdont.models.Turno;
import com.andresFVA.turnosOdont.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @GetMapping
    public List<Turno> getTurnos(){
        return turnoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)//No necesario es el valor por defecto
    public Turno getTurno(@PathVariable Long id){
        return turnoService.findTurno(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Turno createTurno(Turno Turno){
        //TODO: crear TurnoDTO para que no consulte la DB para obtener el paciente y dentista para crear el objeto Turno
        return turnoService.create(Turno);
    }

    @DeleteMapping("/{id}")
    public void deleteTurno(@PathVariable Long id){
        turnoService.deleteTurno(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleEx(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
