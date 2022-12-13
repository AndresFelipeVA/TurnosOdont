package com.andresFVA.turnosOdont.models.Utils;

import com.andresFVA.turnosOdont.models.Turno;
import com.andresFVA.turnosOdont.models.TurnoDto;

public class Mapper {
    public TurnoDto toTurnoDto(Turno turno){
        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setId(turno.getId());

        Person dentist=new Person();
        dentist.setId(turno.getDentist().getMatricula());
        dentist.setFullName(turno.getDentist().getNombre() +" "+turno.getDentist().getApellido());
        turnoDto.setDentist(dentist);

        Person patient=new Person();
        patient.setId(turno.getPatient().getDNI());
        patient.setFullName(turno.getPatient().getNombre() +" "+turno.getPatient().getApellido());
        turnoDto.setPatient(patient);

        turnoDto.setFecha_Hora(turno.getFecha_Hora());

        return turnoDto;
    }

}
