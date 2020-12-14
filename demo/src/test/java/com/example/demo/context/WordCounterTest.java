package com.example.demo.context;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes={Config.class,WordCounterConfiguration.class})
public class WordCounterTest {

    @Autowired WordCounter counter;


    @Test
    public void ShouldStripPunctuation() {
        assertNotNull(counter.count("I, myself, I see the moon").get("i"));
        assertEquals(2, counter.count("I, myself, I see the moon").get("i"));
    }
    @Test
    public void ShouldSkipThe() {
        assertNull(counter.count("I, myself, I see the moon").get("the"));
    }
}
