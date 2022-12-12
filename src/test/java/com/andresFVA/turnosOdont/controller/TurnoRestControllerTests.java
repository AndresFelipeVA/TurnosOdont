package com.andresFVA.turnosOdont.controller;

import com.andresFVA.turnosOdont.controllers.TurnoController;
import com.andresFVA.turnosOdont.services.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TurnoController.class)
public class TurnoRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TurnoService turnoService;

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
