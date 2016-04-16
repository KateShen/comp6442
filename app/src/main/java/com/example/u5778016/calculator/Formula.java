package com.example.u5778016.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by u5721067 on 16/04/16.
 */
public class Formula {
    private static Formula instance = new Formula();

    public static Formula getInstnace() {
        return instance;
    }

    private String transformToSuffix(String infix) {
        Transforming trans = new Transforming(infix);
        return trans.doTransform();
    }

    public double formula(String infix) {

        List<Double> values = new ArrayList<>();
        double result = 0;
        double value1 = 0;
        double value2 = 0;

        String suffix = transformToSuffix(infix);
        String[] nodes = suffix.split("$");
        for (String node : nodes) {
            if (node.equals("")) {
                continue;
            }
            if (node.equals("+")) {
                value2 = values.remove(values.size() - 1);
                value1 = values.remove(values.size() - 1);
                result = value1 + value2;
            } else if (node.equals("-")) {
                value2 = values.remove(values.size() - 1);
                value1 = values.remove(values.size() - 1);
                result = value1 - value2;
            } else if (node.equals("*")) {
                value2 = values.remove(values.size() - 1);
                value1 = values.remove(values.size() - 1);
                result = value1 * value2;
            } else if (node.equals("/")) {
                value2 = values.remove(values.size() - 1);
                value1 = values.remove(values.size() - 1);
                result = value1 / value2;
            } else if (node.equals("^")) {
                value2 = values.remove(values.size() - 1);
                value1 = values.remove(values.size() - 1);
                result = Math.pow(value1, value2);
            } else {
                result = Double.parseDouble(node);
            }
            values.add(result);
        }

        return values.get(0);
    }

  /*  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        double value = Formula.getInstnace().formula(str);
        System.err.println(value);
    }*/
}
