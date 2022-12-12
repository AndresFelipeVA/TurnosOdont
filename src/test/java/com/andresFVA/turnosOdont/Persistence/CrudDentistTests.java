package com.andresFVA.turnosOdont.Persistence;

import com.andresFVA.turnosOdont.models.Dentist;
import com.andresFVA.turnosOdont.repositories.DentistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest//En lugar de @SpringBootTest, asi indicamos que es esto lo que queremos probar para que se cree un contexto
//parcial que contenga los beans específicos de persistencia y no cargue el resto de módulos acelerando la prueba
public class CrudDentistTests {

    @Autowired
    private DentistRepository dentistRepository;

    @Test
    public void shouldPerformCrudOperations(){
        Dentist dentist = new Dentist();
        dentist.setMatricula(67890L);
        dentist.setNombre("FelipeDent");
        dentist.setApellido("AristizabalDent");

        dentistRepository.save(dentist);

        assertThat(dentistRepository.findAll())
                .hasSize(1)
                .first().usingRecursiveComparison().isEqualTo(dentist);

        dentistRepository.deleteById(dentist.getMatricula());

        assertThat(dentistRepository.count()).isZero();
    }
}
