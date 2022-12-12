package com.andresFVA.turnosOdont.Persistence;

import com.andresFVA.turnosOdont.models.Turno;
import com.andresFVA.turnosOdont.repositories.TurnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest//En lugar de @SpringBootTest, asi indicamos que es esto lo que queremos probar para que se cree un contexto
//parcial que contenga los beans específicos de persistencia y no cargue el resto de módulos acelerando la prueba
public class CrudTurnoTests {

    @Autowired
    private TurnoRepository turnoRepository;

    @Test
    public void shouldPerformCrudOperations(){
        Turno turno = new Turno();
        //Por mantener la prueba simple sin inyectar los repositorios de paciente y dentista estos se dejan en Null
        turno.setFecha_Hora(LocalDateTime.parse("2022-12-21T14:35:00"));

        turnoRepository.save(turno);

        assertThat(turnoRepository.findAll())
                .hasSize(1)
                .first().usingRecursiveComparison().isEqualTo(turno);

        turnoRepository.deleteById(turno.getId());

        assertThat(turnoRepository.count()).isZero();
    }
}
