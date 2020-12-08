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
        String ret =  DoubleValueDropZeroes(x) + " " + op + " " + DoubleValueDropZeroes(y) + " = " + DoubleValueDropZeroes(ans);
        return ret;
    }
    @GetMapping("/sum")
    public String SumWork(@RequestParam List<Double> n) {
        String retString = n.stream().map(this::DoubleValueDropZeroes).collect(Collectors.joining(" + "));
        Double ans = n.stream().reduce((sum,val)->sum+val).orElse(Double.POSITIVE_INFINITY);
        return retString + " = " + DoubleValueDropZeroes(ans);
    }


    private String DoubleValueDropZeroes(Double d) {
        String ret = String.valueOf(d);
        if (ret.endsWith(".0"))
            ret =ret.replace(".0","");
        return ret;
    }
}
