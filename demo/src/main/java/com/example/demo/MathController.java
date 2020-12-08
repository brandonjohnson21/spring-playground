package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {
    @GetMapping("/pi/{precision}")
    public String pi(@PathVariable("precision") int precision) {
        return String.format("%."+String.valueOf(precision)+"f",Math.PI);
    }
    @GetMapping("/pi")
    public String pi() {
        return String.format("%.15f", Math.PI);
    }
}
