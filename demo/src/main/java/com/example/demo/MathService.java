package com.example.demo;



import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public static String areaOfCircle(double r) {
        Double ans = Math.PI * Math.pow(r,2);
        return String.format("Area of a circle with a radius of %s is %s",DoubleValueDropZeroes(r),DoubleValueDropZeroes(ans,6));
    }
    public static String areaOfRectangle(double l, double w) {
        Double ans = l*w;
        return String.format("Area of a %sx%s rectangle is %s",DoubleValueDropZeroes(l),DoubleValueDropZeroes(w),DoubleValueDropZeroes(ans));
    }
    public static String area(Map<String, String> params) {
        if (params.containsKey("type")) {
            String type = params.get("type");
            if (type.equals("circle")) {
                if (params.containsKey("radius")) {
                    return areaOfCircle(Double.parseDouble(params.get("radius")));
                }
            } else if (type.equals("rectangle")) {
                if (params.containsKey("width") && params.containsKey("height")) {
                    return areaOfRectangle(Double.parseDouble(params.get("width")), Double.parseDouble(params.get("height")));
                }
            }
        }
        return "Invalid";
    }
    private static String DoubleValueDropZeroes(Double d, int precision) {
        return new DecimalFormat("#." + repeat("#",precision)).format(d);
    }
    private static String DoubleValueDropZeroes(Double d) {
        return DoubleValueDropZeroes(d,16);
    }
    private static String repeat(String s, int num) {
        StringBuilder r = new StringBuilder();
        for (int i=0;i<num;i++) {
            r.append(s);
        }
        return r.toString();
    }
}
