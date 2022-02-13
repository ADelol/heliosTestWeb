package com.helios.testTechnique.controllers;


import com.helios.testTechnique.services.XSpeedItService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class XSpeedItControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private XSpeedItService xSpeedItService;


    @Test
    public void callXSpeedItController() throws Exception {
        given(xSpeedItService.getXSpeedItSolution("974587"))
                .willReturn("9/8/7/7/54 5");

        mockMvc.perform(get("/api/v1/xspeedit?items=974587"))
                .andExpect(status().isOk());

        verify(xSpeedItService, times(1)).getXSpeedItSolution("974587");
    }

    @Test
    public void callXSpeeditControllerException() throws Exception {
        mockMvc.perform(get("/api/v1/xspeedit?items=97ggggg4587"))
                .andExpect(status().is4xxClientError());

        verify(xSpeedItService, times(0)).getXSpeedItSolution("97ggggg4587");
    }
}
