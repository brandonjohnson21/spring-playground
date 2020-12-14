package com.example.demo.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordCounterConfiguration {
    public final Config config;
    WordCounterConfiguration(Config config) {
        this.config=config;
    }
    @Bean public WordCounter getCounter() {
        return new WordCounter(config);
    }
}
