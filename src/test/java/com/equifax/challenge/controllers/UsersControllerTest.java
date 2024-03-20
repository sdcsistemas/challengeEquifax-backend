package com.equifax.challenge.controllers;

import com.equifax.challenge.dtos.UsersDTO;
import com.equifax.challenge.services.LoginService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LoginService loginService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    void testCreateUSer() {
        UsersDTO user = new UsersDTO("sergio.crocetta@gmail.com", "112233");
        when(loginService.insert(any())).then(invocation -> {
            UsersDTO u = invocation.getArgument(0);
            //u.setId(3L);
            return u;
        });

        try {
            mvc.perform(post("/user/insert").contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user)))

                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.email", is("sergio.crocetta@gmail.com")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        verify(loginService).insert(any());
    }

    @Test
    @Order(2)
    void testLogin() {
        UsersDTO user = new UsersDTO("sergio.crocetta@gmail.com", "112233");
        when(loginService.insert(any())).then(invocation -> {
            UsersDTO u = invocation.getArgument(0);
            return u;
        });

        try {
            mvc.perform(post("/user/insert").contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.email", is("sergio.crocetta@gmail.com")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        verify(loginService).insert(any());
    }
}
