package com.example.demo;

import com.example.demo.crud.Lesson;
import com.example.demo.crud.LessonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.demo.crud.Lesson.dateFormat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CRUDTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    LessonRepository repository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @Rollback
    @Transactional
    public void PutTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/lessons").contentType(MediaType.APPLICATION_JSON).content("{\"title\": \"JPA\", \"deliveredOn\": \"2012-01-14\"}")).andExpect(jsonPath("$.title",equalTo("JPA") ));
    }

    @Test
    @Rollback
    @Transactional
    public void GetTest() throws Exception {
        String post = mvc.perform(MockMvcRequestBuilders.post("/lessons").contentType(MediaType.APPLICATION_JSON).content("{\"title\": \"JPA\", \"deliveredOn\": \"2012-01-14\"}")).andExpect(jsonPath("$.title",equalTo("JPA") )).andReturn().getResponse().getContentAsString();
        Lesson posted = mapper.readValue(post, Lesson.class);
        long id = posted.getId();
        mvc.perform(MockMvcRequestBuilders.get("/lessons/" + id)).andExpect(jsonPath("$.title",equalTo("JPA")));
    }

    @Test
    @Rollback
    @Transactional
    public void DeleteTest() throws Exception {
        String post = mvc.perform(MockMvcRequestBuilders.post("/lessons").contentType(MediaType.APPLICATION_JSON).content("{\"title\": \"JPA\", \"deliveredOn\": \"2012-01-14\"}")).andExpect(jsonPath("$.title",equalTo("JPA") )).andReturn().getResponse().getContentAsString();
        Lesson posted = mapper.readValue(post, Lesson.class);
        long id = posted.getId();
        mvc.perform(MockMvcRequestBuilders.delete("/lessons/" + id)).andExpect(MockMvcResultMatchers.content().string(post));
        mvc.perform(MockMvcRequestBuilders.get("/lessons/" + id)).andExpect(MockMvcResultMatchers.content().string("null"));
    }

    @Test
    @Rollback
    @Transactional
    public void PatchTest() throws Exception {
        repository.save(new Lesson("Test","2017-04-12"));
        long id = repository.findByTitle("Test").get(0).getId();
        String update = "{ \"title\":\"Balloons\"}";
        mvc.perform(MockMvcRequestBuilders.patch("/lessons/" + id).contentType(MediaType.APPLICATION_JSON).content(update)).andExpect(jsonPath("$.title",equalTo("Balloons")));
        assertTrue(repository.findById(id).orElseGet(()->new Lesson("FAIL",(Date)null)).getTitle().equals("Balloons"));

    }

    @Test
    @Rollback
    @Transactional
    public void FindTest() throws Exception {
        repository.save(new Lesson("Test","2017-04-12"));
        mvc.perform(MockMvcRequestBuilders.get("/lessons/find/Test"))
                .andExpect(jsonPath("$.title",equalTo("Test")))
                .andExpect(jsonPath("$.deliveredOn",equalTo("2017-04-12")));
    }
    @Test
    @Rollback
    @Transactional
    public void FindBetweenTest() throws Exception {
        List<Lesson> test = Arrays.asList(new Lesson("Test",dateFormat.parse("2017-04-12")),new Lesson("Test2",dateFormat.parse("2017-05-12")));
        test.forEach(repository::save);
        mvc.perform(MockMvcRequestBuilders.get("/lessons/between?date1=2017-01-01&date2=2017-04-31"))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].title",equalTo("Test")))
                .andExpect(jsonPath("$[0].deliveredOn",equalTo("2017-04-12")));
        mvc.perform(MockMvcRequestBuilders.get("/lessons/between?date1=2017-01-01&date2=2017-08-31"))
                .andExpect(jsonPath("$",hasSize(2)));
    }

}
