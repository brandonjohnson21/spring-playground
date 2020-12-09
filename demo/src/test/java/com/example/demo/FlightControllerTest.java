package com.example.demo;

import com.example.demo.flight.Passenger;
import com.example.demo.flight.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.hamcrest.Matchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class FlightControllerTest {
    @Autowired
    private MockMvc mvc;
    ObjectMapper objMapper = new ObjectMapper();

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
                .andExpect(jsonPath("$.Tickets[0].price", org.hamcrest.Matchers.is(200)));

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
                .andExpect(jsonPath("$[0].Tickets[0].price", org.hamcrest.Matchers.is(200)))
                .andExpect(jsonPath("$[1].Departs", org.hamcrest.Matchers.is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", org.hamcrest.Matchers.is("Some other name")))
//                .andExpect(jsonPath("$[1].Tickets[0].Passenger.LastName", org.hamcrest.Matchers.nullValue()))
                .andExpect(jsonPath("$[1].Tickets[0].price", org.hamcrest.Matchers.is(400)));

    }
    @Test
    public void TestTicketTotalString() throws Exception {
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
    @Test
    public void TestTicketTotalObject() throws Exception {
        HashMap<String,Object> payload = new HashMap<String,Object>(){{
            this.put("tickets", Arrays.asList(
                    new Ticket(new Passenger("Ey","Blinkin"),200),
                    new Ticket(new Passenger("Mr","Hood"),100)
                    ));

        }};
        String json = objMapper.writeValueAsString(payload);
        this.mvc.perform(
                post("/flights/tickets/total")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", org.hamcrest.Matchers.is(300)));

    }
    @Test
    public void TestTicketTotalFile() throws Exception {
        String json = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/tickets.json").toURI())));
        this.mvc.perform(
                post("/flights/tickets/total")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", org.hamcrest.Matchers.is(150)));

    }
}
