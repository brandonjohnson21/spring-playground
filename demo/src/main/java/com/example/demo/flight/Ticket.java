package com.example.demo.flight;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    @JsonProperty("Passenger")
    Passenger passenger;
    @JsonProperty("Price")
    Integer price;
    public Ticket(Passenger p, Integer price) {
        this.price=price;
        this.passenger=p;
    }

    public Passenger getPassenger() {
        return passenger;
    }

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
