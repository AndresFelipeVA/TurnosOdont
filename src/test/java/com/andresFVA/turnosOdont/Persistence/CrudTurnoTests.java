package com.andresFVA.turnosOdont.Persistence;

import com.andresFVA.turnosOdont.models.Dentist;
import com.andresFVA.turnosOdont.models.Patient;
import com.andresFVA.turnosOdont.models.Turno;
import com.andresFVA.turnosOdont.repositories.TurnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest//En lugar de @SpringBootTest, asi indicamos que es esto lo que queremos probar para que se cree un contexto
//parcial que contenga los beans específicos de persistencia y no cargue el resto de módulos acelerando la prueba
public class CrudTurnoTests {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldThrowExceptionWithNullValues(){
        Turno turno = new Turno();
        //Paciente y dentista se dejan en Null
        turno.setFecha_Hora(LocalDateTime.parse("2022-12-21T14:35:00"));
        assertThrows(DataIntegrityViolationException.class, ()->turnoRepository.save(turno));
    }
    @Test
    public void shouldPerformCrudOperations(){
        Turno turno = new Turno();
        turno.setPatient(registratePatient());
        turno.setDentist(registrateDentist());
        turno.setFecha_Hora(LocalDateTime.parse("2022-12-21T14:35:00"));

        turnoRepository.save(turno);

        assertThat(turnoRepository.findAll())
                .hasSize(1)
                .first().usingRecursiveComparison().isEqualTo(turno);

        turnoRepository.deleteById(turno.getId());

        assertThat(turnoRepository.count()).isZero();
    }

    private Dentist registrateDentist() {
        Dentist dentist = new Dentist();
        dentist.setMatricula(67890L);
        dentist.setNombre("FelipeDent");
        dentist.setApellido("AristizabalDent");
        entityManager.persistAndFlush(dentist);
        return dentist;
    }

    private Patient registratePatient() {
        Patient patient = new Patient();
        patient.setDNI(123456L);
        patient.setNombre("Andres");
        patient.setApellido("Vallejo");
        patient.setDomicilio("Carrera 72 # 12 A 17");
        entityManager.persistAndFlush(patient);
        return patient;
    }
}
