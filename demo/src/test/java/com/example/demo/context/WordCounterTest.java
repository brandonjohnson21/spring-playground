package com.example.demo.context;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterTest {

    @Test
    public void ShouldStripPunctuation() {
        assertEquals(2, new WordCounter().count("I, myself, I see the moon").get("I"));
    }
}
