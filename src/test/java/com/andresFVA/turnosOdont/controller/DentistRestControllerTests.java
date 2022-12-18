package com.andresFVA.turnosOdont.controller;

import com.andresFVA.turnosOdont.controllers.DentistController;
import com.andresFVA.turnosOdont.services.DentistService;
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
public class DentistRestControllerTests {
    /* Cambiado para aislar controller sin cargar el WebApplicationContext de Spring y asi evitar la seguridad y
    *  realmente hacer una prueba unitaria del REST controller
    *  Implementando la estrategia 1 según:
    *       https://thepracticaldeveloper.com/guide-spring-boot-controller-tests
    * */
    private MockMvc mockMvc;

    @Mock
    private DentistService dentistService;

    @InjectMocks
    private DentistController dentistController;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(dentistController).build();
    }

    @Test
    public void canGetAllDentistTest() throws Exception{//Perform puede lanzar una excepción
        mockMvc.perform(get("/api/v1/dentists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
        verify(dentistService, times(1)).findAll();
    }

    @Test
    public void canCreateDentistTest() throws Exception{//Perform puede lanzar una excepción
        mockMvc.perform(post("/api/v1/dentists"))
                .andExpect(status().isCreated());
    }

}

