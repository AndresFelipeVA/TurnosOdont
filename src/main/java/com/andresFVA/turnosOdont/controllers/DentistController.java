package com.andresFVA.turnosOdont.controllers;

import com.andresFVA.turnosOdont.models.Dentist;
import com.andresFVA.turnosOdont.services.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dentists")
public class DentistController {
    @Autowired
    private DentistService dentistService;

    @GetMapping
    public List<Dentist> getDentists(){
        return dentistService.findAll();
    }

    @GetMapping("/{matricula}")
    @ResponseStatus(HttpStatus.OK)//No necesario es el valor por defecto
    public Dentist getDentist(@PathVariable Long matricula){
        return dentistService.findDentist(matricula);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dentist createDentist(Dentist dentist){
        return dentistService.create(dentist);
    }

    @DeleteMapping("/{matricula}")
    public void deleteDentist(@PathVariable Long matricula){
        dentistService.deleteDentist(matricula);
    }

}
