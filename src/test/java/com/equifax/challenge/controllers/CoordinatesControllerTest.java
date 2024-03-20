package com.equifax.challenge.controllers;

import com.equifax.challenge.Datos;
import com.equifax.challenge.dtos.CoordinatesDTO;
import com.equifax.challenge.services.CoordinatesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoordinatesController.class)
class CoordinatesControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CoordinatesService coordinatesService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    void testCreateUSer() {
        CoordinatesDTO dto = new CoordinatesDTO(1L, "777", "333",
                "Latidud Descentende al norte", "Longitud Descentende al sur");
        when(coordinatesService.insert(any())).then(invocation -> {
            CoordinatesDTO c = invocation.getArgument(0);
            c.setId(3L);
            return c;
        });

        try {
            mvc.perform(post("/coordinates/insert").contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))

                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id", is(3)))
                    .andExpect(jsonPath("$.lat", is("777")))
                    .andExpect(jsonPath("$.lng", is("333")))
                    .andExpect(jsonPath("$.description", is("Latidud Descentende al norte")))
                    .andExpect(jsonPath("$.observation", is("Longitud Descentende al sur")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        verify(coordinatesService).insert(any());
    }

    @Test
    @Order(2)
    void testFindByLatAndLng() throws Exception {
        when(coordinatesService.findByLatAndLng("777", "333")).thenReturn(Datos.createCoordinates());

        mvc.perform(get("/coordinates/findByLatAndLng/777/333").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.lat", is("777")))
                .andExpect(jsonPath("$.lng", is("333")))
                .andExpect(jsonPath("$.description", is("Latidud Descentende al norte")))
                .andExpect(jsonPath("$.observation", is("Longitud Descentende al sur")));

        verify(coordinatesService).findByLatAndLng("777", "333");
    }

}
