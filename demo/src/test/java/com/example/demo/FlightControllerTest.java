package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class FlightControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void TestFlightsFlight() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", org.hamcrest.Matchers.is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", org.hamcrest.Matchers.is("Some name")))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName", org.hamcrest.Matchers.is("Some other name")))
                .andExpect(jsonPath("$.tickets[0].price", org.hamcrest.Matchers.is(200)));

    }
    @Test
    public void TestFlights() throws Exception {
        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departs", org.hamcrest.Matchers.is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", org.hamcrest.Matchers.is("Some name")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", org.hamcrest.Matchers.is("Some other name")))
                .andExpect(jsonPath("$[0].tickets[0].price", org.hamcrest.Matchers.is(200)))
                .andExpect(jsonPath("$[1].departs", org.hamcrest.Matchers.is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", org.hamcrest.Matchers.is("Some other name")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.lastName", org.hamcrest.Matchers.nullValue()))
                .andExpect(jsonPath("$[1].tickets[0].price", org.hamcrest.Matchers.is(400)));

    }
}
