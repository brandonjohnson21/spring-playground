package com.example.demo.crud;

import org.springframework.data.repository.CrudRepository;

public interface LessonRepository  extends CrudRepository<Lesson, Long> {
}
