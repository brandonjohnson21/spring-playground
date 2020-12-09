package com.example.demo.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger {
    @JsonProperty("FirstName")
    private String firstName;
    @JsonIgnore
    private String lastName;
    public Passenger(String first, String last) {
        this.firstName=first;
        this.lastName=last;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
