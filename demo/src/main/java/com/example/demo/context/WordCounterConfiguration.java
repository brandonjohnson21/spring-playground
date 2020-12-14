package com.example.demo.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordCounterConfiguration {
    @Bean public WordCounter getCounter(Config config) {
        return new WordCounter(config);
    }
}
