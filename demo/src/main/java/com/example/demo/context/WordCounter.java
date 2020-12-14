package com.example.demo.context;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class WordCounter {
    private final Config config;

    WordCounter(Config config) {
        this.config=config;

    }
    public Map<String,Integer> count(String string) {
        HashMap<String, Integer> counts = new HashMap<>();
        string = string.replaceAll("[^a-zA-Z0-9 ]","");

        Arrays.stream(string.split(" "))
                .filter(s->(config.getWords() == null || Arrays.stream(config.getWords().getSkip()).noneMatch(s1->s1.equals(s))))
                .map(s->(!config.isCaseSensitive())?s.toLowerCase():s)
                .forEach(s->counts.compute(s,(key,current)->(current==null)?1:current+1));
        return counts;
    }


}
