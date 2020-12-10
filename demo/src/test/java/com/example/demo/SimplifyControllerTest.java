package com.example.demo;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
public class SimplifyControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void TestSimplifyDetailed() throws Exception {
        String json = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/simplifyData.json").toURI())));
        String expectedJson = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/simplifyDataDetailed.json").toURI())));
        expectedJson = expectedJson.replace("\r\n","\n").replace("\n",System.lineSeparator()); // all line endings to \n then to system line ending
        this.mvc.perform(
                post("/activities/simplify")
                        .accept("application/vnd.galvanize.detailed+json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(content().string(expectedJson));
    }
    @Test
    public void TestSimplifyCompact() throws Exception {
        String json = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/simplifyData.json").toURI())));
        String expectedJson = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/simplifyDataCompact.json").toURI())));
        expectedJson = expectedJson.replace("\r\n","\n").replace("\n",System.lineSeparator()); // all line endings to \n then to system line ending
        this.mvc.perform(
                post("/activities/simplify")
                        .accept("application/vnd.galvanize.compact+json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(content().string(expectedJson));
    }
}
