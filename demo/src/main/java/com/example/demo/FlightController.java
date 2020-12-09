package com.example.demo;

import com.example.demo.flight.Flight;
import com.example.demo.flight.Passenger;
import com.example.demo.flight.Ticket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.*;

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
}
