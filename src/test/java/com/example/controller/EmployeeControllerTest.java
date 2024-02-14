package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.service.AdministratorService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdministratorService sAdministratorService;

    // @Test
    // public void testShowDetail() throws Exception{
    //    mvc.perform(get("/employees"))
    //     .andExpect(status().is(400));
    // }

    // @Test
    // public void testShowList() throws Exception{
    //     mvc
    //       .perform(post("/login"))
    //       .andExpect(status().is(302))
    //       .andExpect(resirectedUrl("/login"))
    // }
}
