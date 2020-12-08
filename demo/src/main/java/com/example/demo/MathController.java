package com.example.demo;

import org.springframework.web.bind.annotation.*;

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
    public String calc(@RequestParam int x, @RequestParam int y, @RequestParam(required=false) String operation) {
        operation = (operation==null)?"add":operation;
        switch (operation) {
            case "subtract":
                return String.valueOf(x - y);
            case "divide":
                if (y != 0)
                    return String.valueOf(x / y);
                else
                    return "Infinity";
            case "multiply":
                return String.valueOf(x * y);
            default:
                return String.valueOf(x + y);
        }
    }
}
