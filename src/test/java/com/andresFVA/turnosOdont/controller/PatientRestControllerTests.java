package com.andresFVA.turnosOdont.controller;

import com.andresFVA.turnosOdont.controllers.PatientController;
import com.andresFVA.turnosOdont.models.Patient;
import com.andresFVA.turnosOdont.services.PatientService;
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

@WebMvcTest(PatientController.class)
public class PatientRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PatientService patientService;

    @Test
    public void canGetAllPatientTest() throws Exception{//Perform puede lanzar una excepción
        mockMvc.perform(get("/api/v1/patients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
        verify(patientService, times(1)).findAll();
    }

    @Test
    public void canCreatePatientTest() throws Exception{//Perform puede lanzar una excepción
        mockMvc.perform(post("/api/v1/patients"))
                .andExpect(status().isCreated());
    }

}
