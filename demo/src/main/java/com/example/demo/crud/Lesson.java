package com.example.demo.crud;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="lessons")
public class Lesson {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.SingleView.class)
    private Long id;
    @JsonView(Views.SingleView.class)
    private String title;


    @Column(columnDefinition = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView(Views.ListView.class)
    private Date deliveredOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeliveredOn() {
        return deliveredOn;
    }

    public void setDeliveredOn(Date deliveredOn) {
        this.deliveredOn = deliveredOn;
    }
    public Lesson() { }
    public Lesson(String title, Date date) {
        this.title = title;
        this.deliveredOn = date;
    }
    public Lesson(String title, String date) throws ParseException {
        this.title = title;
        this.deliveredOn = dateFormat.parse(date);
    }
}
