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

import java.sql.Date;

import static org.hamcrest.core.IsEqual.equalTo;
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
        Lesson test = new Lesson();
        test.setTitle("Test");
        test.setDeliveredOn(Date.valueOf("2017-04-12"));
        String post = mvc.perform(MockMvcRequestBuilders.post("/lessons").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(test))).andExpect(jsonPath("$.title",equalTo("Test") )).andReturn().getResponse().getContentAsString();
        Lesson posted = mapper.readValue(post, Lesson.class);
        long id = posted.getId();
        String update = "{ \"title\":\"Balloons\"}";
        mvc.perform(MockMvcRequestBuilders.patch("/lessons/" + id).contentType(MediaType.APPLICATION_JSON).content(update)).andExpect(jsonPath("$.title",equalTo("Balloons")));
        mvc.perform(MockMvcRequestBuilders.get("/lessons/" + id)).andExpect(jsonPath("$.title",equalTo("Balloons")));
    }


}
