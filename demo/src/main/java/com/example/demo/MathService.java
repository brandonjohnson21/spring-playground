package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MathService {
    private double x = 0;
    private double y = 0;
    private List<Double> n = new ArrayList<>();
    private String operation = "add";

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<Double> getN() {
        return n;
    }

    public void setN(List<Double> n) {
        this.n = n;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String calculate() {
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
    public String sum() {
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
