package com.andresFVA.turnosOdont.Persistence;

import com.andresFVA.turnosOdont.models.Patient;
import com.andresFVA.turnosOdont.repositories.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest//En lugar de @SpringBootTest, asi indicamos que es esto lo que queremos probar para que se cree un contexto
//parcial que contenga los beans específicos de persistencia y no cargue el resto de módulos acelerando la prueba
public class CrudPatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void shouldPerformCrudOperations(){
        Patient patient = new Patient();
        patient.setDNI(123456L);
        patient.setNombre("Andres");
        patient.setApellido("Vallejo");
        patient.setDomicilio("Carrera 72 # 12 A 17");
//        patient.setFecha_alta(LocalDateTime.parse("2022-12-21T14:35:00"));

        patientRepository.save(patient);

        assertThat(patientRepository.findAll())
                .hasSize(1)
                .first().usingRecursiveComparison().isEqualTo(patient);

        patientRepository.deleteById(patient.getDNI());

        assertThat(patientRepository.count()).isZero();
    }
}
