package com.example.demo.simplify;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.Date;

public class Status {
    String text="";
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "UTC")
    Date date = Date.from(Instant.now());

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
