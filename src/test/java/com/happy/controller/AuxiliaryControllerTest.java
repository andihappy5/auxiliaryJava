package com.happy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuxiliaryController.class)
public class AuxiliaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHappyJson() throws Exception {
        mockMvc.perform(get("/auxiliary/jave"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"aim\":\"happy\"}"));
    }
}
