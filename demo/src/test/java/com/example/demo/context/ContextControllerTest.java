package com.example.demo.context;

import com.example.demo.crud.LessonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ContextController.class)
public class ContextControllerTest {
    @Autowired MockMvc mvc;

    @MockBean WordCounter counter;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init () {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("believe",2);
        when(counter.count(anyString())).thenReturn(map);
    }
    @Test
    public void testEndpoint() throws Exception {
        mvc.perform(post("/words/count").content("I am Iron Man"))
                .andExpect(jsonPath("$.believe",equalTo(2)));
    }
}
