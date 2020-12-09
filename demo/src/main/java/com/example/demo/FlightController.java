package com.example.demo;

import com.example.demo.flight.Flight;
import com.example.demo.flight.Passenger;
import com.example.demo.flight.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class FlightController {
    // /flights/flight
    @GetMapping("/flights/flight")
    public Flight getFlight() {
        Calendar departdate = new Calendar.Builder().setTimeZone(TimeZone.getTimeZone("UTC")).setDate(2017,3,21).setTimeOfDay(14,34,00).build();
        return new Flight(departdate.getTime(), new Ticket[]{new Ticket(new Passenger("Some name", "Some other name"), 200)});
    }
    @GetMapping("/flights")
    public List<Flight> getFlights() {
        Calendar departdate = new Calendar.Builder().setTimeZone(TimeZone.getTimeZone("UTC")).setDate(2017,3,21).setTimeOfDay(14,34,00).build();
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(departdate.getTime(), new Ticket[]{new Ticket(new Passenger("Some name", "Some other name"), 200)}));
        flights.add(new Flight(departdate.getTime(), new Ticket[]{new Ticket(new Passenger("Some other name", null), 400)}));
        return flights;
    }
    @PostMapping("/flights/tickets/total")
    public HashMap<String,Object> calculateTotal(@RequestBody HashMap<String, ArrayList<Ticket>> body) {
        if (body.containsKey("tickets")) {
            return new HashMap<String,Object>(){
                {
                    this.put("result",body.get("tickets").stream().map(Ticket::getPrice).reduce(Integer::sum));
                }
            };
        }
        return new HashMap<>();
    }
}
