package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathServiceTest {
    @Test
    public void AddTest() throws Exception {
        MathService ms = new MathService("add",2,3);
        assertEquals("2 + 3 = 5",ms.calculate());
    }
    @Test
    public void SubtractTest() throws Exception {
        MathService ms = new MathService("subtract",114,3);
        assertEquals("114 - 3 = 111",ms.calculate());
    }
    @Test
    public void DivideTest() throws Exception {
        MathService ms = new MathService("divide",15,3);
        assertEquals("15 / 3 = 5",ms.calculate());
    }
    @Test
    public void MultiplyTest() throws Exception {
        MathService ms = new MathService("multiply",17,2);
        assertEquals("17 * 2 = 34",ms.calculate());
    }
    @Test
    public void NoOpTest() throws Exception {
        MathService ms = new MathService(null,17,2);
        assertEquals("17 + 2 = 19",ms.calculate());
    }
    @Test
    public void SumTest() throws Exception {
        MathService ms = new MathService(Arrays.asList(17.0d,2d,12d));
        assertEquals("17 + 2 + 12 = 31",ms.sum());
    }
}
