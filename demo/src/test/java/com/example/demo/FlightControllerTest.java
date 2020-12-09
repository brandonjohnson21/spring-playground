package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
               // .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", org.hamcrest.Matchers.is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", org.hamcrest.Matchers.is("Some name")))
//                .andExpect(jsonPath("$[0].Tickets.Passenger.LastName", org.hamcrest.Matchers.is("Some other name")))
                .andExpect(jsonPath("$.Tickets[0].Price", org.hamcrest.Matchers.is(200)));

    }
    @Test
    public void TestFlights() throws Exception {
        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", org.hamcrest.Matchers.is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", org.hamcrest.Matchers.is("Some name")))
//                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", org.hamcrest.Matchers.is("Some other name")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", org.hamcrest.Matchers.is(200)))
                .andExpect(jsonPath("$[1].Departs", org.hamcrest.Matchers.is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", org.hamcrest.Matchers.is("Some other name")))
//                .andExpect(jsonPath("$[1].Tickets[0].Passenger.LastName", org.hamcrest.Matchers.nullValue()))
                .andExpect(jsonPath("$[1].Tickets[0].Price", org.hamcrest.Matchers.is(400)));

    }
    @Test
    public void TestTicketTotal() throws Exception {
        this.mvc.perform(
                post("/flights/tickets/total")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                 "    \"tickets\": [\n" +
                                 "      {\n" +
                                 "        \"passenger\": {\n" +
                                 "          \"firstName\": \"Some name\",\n" +
                                 "          \"lastName\": \"Some other name\"\n" +
                                 "        },\n" +
                                 "        \"price\": 200\n" +
                                 "      },\n" +
                                 "      {\n" +
                                 "        \"passenger\": {\n" +
                                 "          \"firstName\": \"Name B\",\n" +
                                 "          \"lastName\": \"Name C\"\n" +
                                 "        },\n" +
                                 "        \"price\": 150\n" +
                                 "      }\n" +
                                 "    ]\n" +
                                 "  }\n")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", org.hamcrest.Matchers.is(350)));

    }
}
