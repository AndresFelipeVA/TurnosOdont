package com.andresFVA.turnosOdont.controller;

import com.andresFVA.turnosOdont.controllers.TurnoController;
import com.andresFVA.turnosOdont.services.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TurnoRestControllerTests {
    /* Cambiado para aislar controller sin cargar el WebApplicationContext de Spring y asi evitar la seguridad y
     *  realmente hacer una prueba unitaria del REST controller
     *  Implementando la estrategia 1 según:
     *       https://thepracticaldeveloper.com/guide-spring-boot-controller-tests
     * */
    private MockMvc mockMvc;

    @Mock
    private TurnoService turnoService;

    @InjectMocks
    private TurnoController turnoController;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(turnoController).build();
    }

    @Test
    public void canGetAllTurnoTest() throws Exception{//Perform puede lanzar una excepción
        mockMvc.perform(get("/api/v1/turnos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
        verify(turnoService, times(1)).findAll();
    }

    @Test
    public void canCreateTurnoTest() throws Exception{//Perform puede lanzar una excepción
        mockMvc.perform(post("/api/v1/turnos"))
                .andExpect(status().isCreated());
    }

}
