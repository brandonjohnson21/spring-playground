package com.example.demo.context;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("word-count")
public class Config {

    private Words words;
    private Boolean casesensitive;

    public Boolean isCaseSensitive() {
        return casesensitive;
    }

    public void setCaseSensitive(Boolean caseSensitive) {
        this.casesensitive = caseSensitive;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public static class Words {
        String[] skip;

        public String[] getSkip() {
            return skip;
        }

        public void setSkip(String[] skip) {
            this.skip = skip;
        }
    }



}