package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest
public class MathTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void PiTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/math/pi")).andExpect(MockMvcResultMatchers.content().string("3.141592653589793"));

    }
}
