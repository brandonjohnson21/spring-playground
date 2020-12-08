package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public String calc(@RequestParam(defaultValue = "add") String operation, double x, double y) {
        return MathService.calculate(operation, Arrays.asList(x,y));
    }
    @PostMapping("/sum")
    public String SumWork(@RequestParam List<Double> n) {
        return MathService.calculate("add",n);
    }
    @RequestMapping("/volume/{length}/{width}/{height}")
    public String Volume( @PathVariable Double length,  @PathVariable Double width,  @PathVariable Double height) {
        return MathService.volume(Arrays.asList(length,width,height));
    }
    @PostMapping("/area")
    public String Area(@RequestParam Map<String, String> params) {
        return MathService.area(params);
    }


}
