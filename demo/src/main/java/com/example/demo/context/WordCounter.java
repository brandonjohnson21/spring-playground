package com.example.demo.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class WordCounter {
    public Map<String,Integer> count(String string) {
        HashMap<String, Integer> counts = new HashMap<>();
        string = string.replaceAll("[^a-zA-Z0-9 ]","");
        Arrays.stream(string.split(" ")).forEach(s->counts.compute(s,(key,current)->(current==null)?1:current+1));
        return counts;
    }

}
