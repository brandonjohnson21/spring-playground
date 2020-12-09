package com.example.demo.flight;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Flight {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="UTC")

    Date departs;
    List<Ticket> tickets= new ArrayList<>();
    public Flight(Date departs, Ticket[] tickets) {
        this.tickets.addAll(Arrays.asList(tickets));
        this.departs=departs;
    }

    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
