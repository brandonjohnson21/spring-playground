package com.example.demo.crud;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
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
    public Optional<Lesson> deleteOne(@PathVariable long lessonId) {
        Optional<Lesson> lesson =this.repository.findById(lessonId);
        if (lesson.isPresent()) {
            this.repository.deleteById(lessonId);
        }
        return lesson;

    }
    @PatchMapping("/{lessonId}")
    public Optional<Lesson> updateOne(@PathVariable long lessonId, @RequestBody Lesson updated) {
        Optional<Lesson> lesson =this.repository.findById(lessonId);
        lesson.ifPresent(l->{
            if (updated.getTitle() != null) {
                l.setTitle(updated.getTitle());
            }
            if (updated.getDeliveredOn() != null) {
                l.setDeliveredOn(updated.getDeliveredOn());
            }
            this.repository.save(l);
        });
        return lesson;
    }

    @GetMapping("/find/{lessonTitle}")
    @JsonView(Views.ListView.class)
    public Lesson findByTitle(@PathVariable String lessonTitle) {
        List<Lesson> list =this.repository.findByTitle(lessonTitle);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    @GetMapping("/between")
    @JsonView(Views.ListView.class)
    public List<Lesson> findByDate(@RequestParam String date1, @RequestParam String date2) throws ParseException {
        return this.repository.findByDeliveredOnBetween(Lesson.dateFormat.parse(date1),Lesson.dateFormat.parse(date2));
    }
}
