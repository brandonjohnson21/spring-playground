package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

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
    @GetMapping("/calculate")
    public String calc(MathService mathService) {
        return mathService.calculate();
    }
    @PostMapping("/sum")
    public String SumWork(MathService mathService) {
        return mathService.sum();
    }



}
