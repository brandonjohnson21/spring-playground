package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathServiceTest {
    @Test
    public void AddTest() throws Exception {
        assertEquals("2 + 3 = 5",MathService.calculate("add",Arrays.asList(2d,3d)));
    }
    @Test
    public void SubtractTest() throws Exception {
        assertEquals("114 - 3 = 111",MathService.calculate("subtract",Arrays.asList(114d,3d)));
    }
    @Test
    public void DivideTest() throws Exception {
        assertEquals("15 / 3 = 5",MathService.calculate("divide",Arrays.asList(15d,3d)));
    }
    @Test
    public void MultiplyTest() throws Exception {
        assertEquals("17 * 2 = 34",MathService.calculate("multiply",Arrays.asList(17d,2d)));
    }
    @Test
    public void NoOpTest() throws Exception {
        assertEquals("17 + 2 = 19",MathService.calculate(null,Arrays.asList(17d,2d)));
    }
    @Test
    public void SumTest() throws Exception {
        assertEquals("17 + 2 + 12 = 31",MathService.sum(Arrays.asList(17.0d,2d,12d)));
    }
}
