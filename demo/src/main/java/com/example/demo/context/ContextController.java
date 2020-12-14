package com.example.demo.context;

import com.example.demo.crud.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ContextController {

    private final WordCounter counter;
    ContextController(WordCounter counter) {
        this.counter = counter;
    }
    @PostMapping("/words/count")
    public Map<String, Integer> countWords(@RequestBody String body) {
        return counter.count(body);
    }

}
