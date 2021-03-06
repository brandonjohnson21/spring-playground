package com.example.demo.flight;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

    Passenger passenger;
    Integer price;
    public Ticket(Passenger p, Integer price) {
        this.price=price;
        this.passenger=p;
    }
    @JsonProperty("Passenger")
    public Passenger getPassenger() {
        return passenger;
    }
    @JsonProperty("passenger")
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
