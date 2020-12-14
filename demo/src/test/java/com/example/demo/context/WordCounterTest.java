package com.example.demo.context;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes={WordCounterConfiguration.class,Config.class, DemoApplication.class})
//@EnableConfigurationProperties // We need this to read properties or we need to include the main app in the class list above
@TestPropertySource(properties = {
        "word-count.caseSensitive=false",
        "word-count.words.skip[0]=the",
        "word-count.words.skip[1]=an",
        "word-count.words.skip[2]=a"
})
public class WordCounterTest {

    @Autowired WordCounter counter;
    @Autowired Config config;
    @Test
    public void ShouldStripPunctuation() {
        assertEquals(2, counter.count("I, myself, I see the moon").get("i"));
    }
    @Test
    public void ShouldSkipThe() {
        assertNull(counter.count("I, myself, I see the moon").get("the"));
    }
    @Test
    public void ShouldBeCaseInsensitive() {
        assertNotNull(counter.count("KAPOW").get("kapow"));
    }
}
