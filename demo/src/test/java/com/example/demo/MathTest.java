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
        mvc.perform(MockMvcRequestBuilders.get("/math/pi/5")).andExpect(MockMvcResultMatchers.content().string("3.14159"));
        mvc.perform(MockMvcRequestBuilders.get("/math/pi")).andExpect(MockMvcResultMatchers.content().string("3.141592653589793"));

    }
    @Test
    public void AddTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/math/calculate?operation=add&x=2&y=3")).andExpect(MockMvcResultMatchers.content().string("5"));
    }
    @Test
    public void SubtractTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=114&y=3")).andExpect(MockMvcResultMatchers.content().string("111"));
    }
    @Test
    public void DivideTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=15&y=3")).andExpect(MockMvcResultMatchers.content().string("5"));
    }
    @Test
    public void MultiplyTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=17&y=2")).andExpect(MockMvcResultMatchers.content().string("34"));
    }
    @Test
    public void NoOpTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/math/calculate?x=17&y=2")).andExpect(MockMvcResultMatchers.content().string("19"));
    }
}
