package com.example.demo;

import com.example.demo.simplify.Activity;
import com.example.demo.simplify.GalvanizePrettyPrinter;
import com.example.demo.simplify.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class SimplifyController {
    static ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    static DefaultPrettyPrinter prettyPrinter = new GalvanizePrettyPrinter(DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
    static {
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        om.setDefaultPrettyPrinter(prettyPrinter);
    }
    @PostMapping(value = "/activities/simplify", produces = "application/vnd.galvanize.compact+json")
    @JsonView(Views.DetailView.class)
    public String simplifyActivities(@RequestBody HashMap<String, List<Activity>> data) throws JsonProcessingException {
        if (data.containsKey("activities")) {
            String ret = "";
            List<Activity> list = data.get("activities");
            return om.writerWithView(Views.ListView.class).withDefaultPrettyPrinter().writeValueAsString(list);
        }else{
            throw new InvalidDataException();
        }
    }
    @PostMapping(value = "/activities/simplify", produces = "application/vnd.galvanize.detailed+json")
    @JsonView(Views.DetailView.class)
    public String detailedSimplifyActivities(@RequestBody HashMap<String, List<Activity>> data) throws JsonProcessingException {
        if (data.containsKey("activities")) {
            String ret = "";
            List<Activity> list = data.get("activities");
            return om.writerWithView(Views.DetailView.class).withDefaultPrettyPrinter().writeValueAsString(list);
        }else{
            throw new InvalidDataException();
        }
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    static
    class InvalidDataException extends RuntimeException {}
}
