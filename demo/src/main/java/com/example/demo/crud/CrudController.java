package com.example.demo.crud;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Lessons")
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
}
