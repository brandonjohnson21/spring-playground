package com.example.demo.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger {

    private String firstName;

    private String lastName;
    public Passenger(String first, String last) {
        this.firstName=first;
        this.lastName=last;
    }
    @JsonProperty("FirstName")
    public String getFirstName() {
        return firstName;
    }
    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @JsonIgnore
    public String getLastName() {
        return lastName;
    }
    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
