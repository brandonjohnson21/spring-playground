package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class MathService {
    private static HashMap<String, BinaryOperator<Double>> operations = new HashMap<>();
    private static HashMap<String, String> operationString = new HashMap<>();
    static {
        operations.put("add", Double::sum);
        operations.put("subtract", (a,b)->a-b);
        operations.put("divide",(a,b)->a/b);
        operations.put("multiply",(a,b)->a*b);
        operationString.put("add", "+");
        operationString.put("subtract", "-");
        operationString.put("divide","/");
        operationString.put("multiply","*");
    }
    public static String calculate(String op, List<Double> values) {
        Double ans = values.stream().reduce(operations.getOrDefault(op,operations.get("add"))).orElse(0d);
        String retString = values.stream().map(MathService::DoubleValueDropZeroes).collect(Collectors.joining(" " + operationString.getOrDefault(op,operationString.get("add")) + " "));
        return retString + " = " + DoubleValueDropZeroes(ans);
    }
    public static String sum(List<Double> values) {
        return calculate("add",values);
    }
    public static String volume(List<Double> values) {
        Double ans = values.stream().reduce(operations.get("multiply")).orElse(0d);
        return String.format("The volume of a %s rectangle is %s",values.stream().map(MathService::DoubleValueDropZeroes).collect(Collectors.joining("X")),DoubleValueDropZeroes(ans));
    }

    private static String DoubleValueDropZeroes(Double d) {
        String ret = String.valueOf(d);
        if (ret.endsWith(".0"))
            ret =ret.replace(".0","");
        return ret;
    }

}
