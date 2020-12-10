package com.example.demo.crud;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class CrudController {
    private final LessonRepository repository;
    CrudController(LessonRepository repo) {
        this.repository=repo;
    }
    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{lessonId}")
    @JsonView(Views.SingleView.class)
    public Optional<Lesson> findOne(@PathVariable long lessonId) {
        return this.repository.findById(lessonId);
    }
    @DeleteMapping("/{lessonId}")
    @JsonView(Views.ListView.class)
    public Optional<Lesson> deleteOne(@PathVariable long lessonId) {
        Optional<Lesson> lesson =this.repository.findById(lessonId);
        if (lesson.isPresent()) {
            this.repository.deleteById(lessonId);
        }
        return lesson;

    }

}
