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
    public String calc(@RequestParam double x, @RequestParam double y, @RequestParam(required=false) String operation) {
        operation = (operation==null)?"add":operation;
        double ans = 0;
        String op = "+";
        switch (operation) {
            case "subtract":
                ans = x - y;
                op="-";
                break;
            case "divide":
                ans=x / y;
                op="/";
                break;
            case "multiply":
                ans = x * y;
                op="*";
            break;
            default:
                ans=x + y;
                op="+";
                break;
        }
        return x + " " + op + " " + y + " = " + ans;
    }
}
