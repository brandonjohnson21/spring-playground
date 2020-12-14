package com.example.demo.context;

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
                .filter(s->(config.getWords() == null || !config.getWords().getSkip().contains(s)))
                .map(s->(!config.isCaseSensitive())?s.toLowerCase():s)
                .forEach(s->counts.compute(s,(key,current)->(current==null)?1:current+1));
        List<String>  strings = Arrays.stream(string.split(" ")).collect(Collectors.toList());
        strings = strings.stream().filter(s->(config.getWords() == null || !config.getWords().getSkip().contains(s))).collect(Collectors.toList());
        strings = strings.stream().map(s->(!config.isCaseSensitive())?s.toLowerCase():s).collect(Collectors.toList());
        return counts;
    }


}
