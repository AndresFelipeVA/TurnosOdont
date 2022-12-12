package com.andresFVA.turnosOdont.Persistence;

import com.andresFVA.turnosOdont.models.Dentist;
import com.andresFVA.turnosOdont.models.Patient;
import com.andresFVA.turnosOdont.models.Turno;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest//En lugar de @SpringBootTest, asi indicamos que es esto lo que queremos probar para que se cree un contexto
//parcial que contenga los beans específicos de persistencia y no cargue el resto de módulos acelerando la prueba
public class DbBasicCnxTests {

    @PersistenceContext//Usamos entityManager para probar directamente la funcionalidad con la base de
    private EntityManager entityManager;//datos sin usar Spring Data JPA (JpaRepository o CrudRepository)

    @Test
    void PatientCanBeSaved() {
        Patient patient = crearPatient();

        entityManager.persist(patient);

        final TypedQuery<Patient> results = entityManager.createQuery("select p from  Patient p", Patient.class);
        final List<Patient> resultList = results.getResultList();

        assertThat(resultList)
                .hasSize(1)
                .first().isEqualTo(patient);
    }


    @Test
    void DentistCanBeSaved() {
        Dentist dentist = crearDentist();

        entityManager.persist(dentist);

        final TypedQuery<Dentist> results = entityManager.createQuery("select p from  Dentist p", Dentist.class);
        final List<Dentist> resultList = results.getResultList();

        assertThat(resultList)
                .hasSize(1)
                .first().isEqualTo(dentist);
    }


    @Test
    void TurnoCanBeSaved() {
        Patient patient = crearPatient();
        Dentist dentist = crearDentist();
        entityManager.persist(patient);
        entityManager.persist(dentist);

        Turno turno = new Turno();
        turno.setDentist(dentist);
        turno.setPatient(patient);
        turno.setFecha_Hora(LocalDateTime.parse("2022-12-21T14:35:00"));

        entityManager.persist(turno);

        final TypedQuery<Turno> results = entityManager.createQuery("select p from  Turno p", Turno.class);
        final List<Turno> resultList = results.getResultList();

        assertThat(resultList)
                .hasSize(1)
                .first().isEqualTo(turno);
    }

    private Dentist crearDentist() {
        Dentist dentist = new Dentist();
        dentist.setMatricula(67890L);
        dentist.setNombre("FelipeDent");
        dentist.setApellido("AristizabalDent");
        return dentist;
    }
    private Patient crearPatient() {
        Patient patient = new Patient();
        patient.setDNI(123456L);
        patient.setNombre("Andres");
        patient.setApellido("Vallejo");
        patient.setDomicilio("Carrera 72 # 12 A 17");
//        patient.setFecha_alta(LocalDateTime.parse("2022-12-21T14:35:00"));
        return patient;
    }
}
