package com.helios.testTechnique.controllers;


import com.helios.testTechnique.services.FizzBuzzService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FizzBuzzControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FizzBuzzService fizzBuzzService;


    @Test
    public void callFizzBuzzController() throws Exception {
        given(fizzBuzzService.applyFizzBuzz(2,4,4,"fizz","buzz"))
                .willReturn(List.of("1", "fizz", "3", "fizzbuzz"));

        mockMvc.perform(get("/api/v1/fizzbuzz?int1=2&int2=4&limit=4&str1=fizz&str2=buzz"))
                .andExpect(status().isOk());

        verify(fizzBuzzService, times(1)).applyFizzBuzz(2,4,4,"fizz","buzz");
    }

    @Test
    public void callFizzBuzzControllerException() throws Exception {
        mockMvc.perform(get("/api/v1/fizzbuzz?int1=2&int2=0&limit=4&str1=fizz&str2=buzz"))
                .andExpect(status().is4xxClientError());

        verify(fizzBuzzService, times(0)).applyFizzBuzz(2,0,4,"fizz","buzz");
    }
}
