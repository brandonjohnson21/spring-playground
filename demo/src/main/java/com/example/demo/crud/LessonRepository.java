package com.example.demo.crud;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository  extends CrudRepository<Lesson, Long> {
    List<Lesson> findByTitle(String title);
    List<Lesson> findByDeliveredOnBetween(Date from, Date to);

}
