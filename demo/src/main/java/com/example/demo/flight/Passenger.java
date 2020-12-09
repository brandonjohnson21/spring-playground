package com.example.demo.flight;

public class Passenger {
    private String firstName;
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
