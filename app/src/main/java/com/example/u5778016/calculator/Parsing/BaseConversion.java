package com.example.u5778016.calculator.Parsing;

/**
 * Created by u5319900 on 14/05/16.
 */
public class BaseConversion {
    public static double Binary_to_Decimal(String b_string) {
        double d_number = 0;
        String whole,fraction;
        if(b_string.contains(".")) {
            String b_part[] = b_string.split("\\.");
            whole = b_part[0];
            fraction = b_part[1];

            for (int i = 0; i < fraction.length(); i++) {
                d_number += Character.getNumericValue(fraction.charAt(i)) * Math.pow(2, -i - 1);
            }
        }else{
            whole = b_string;
        }

        for (int i = whole.length(); i > 0; i--) {
            d_number += Character.getNumericValue(whole.charAt(i - 1)) * Math.pow(2, whole.length() - i);
        }

        return d_number;
    }

    public static String Decimal_to_Binary(double d_number) {
        double whole = Math.floor(d_number);
        double fraction = d_number - whole;
        int precision = 9;
        StringBuilder b_string = new StringBuilder();
        b_string.append(Integer.toBinaryString((int) whole));

        if (fraction > 0) {
            b_string.append(".");
            double remainder = fraction;
            while (remainder > 0 && precision > 0) {
                remainder *= 2;
                if (remainder >= 1) {
                    b_string.append(1);
                    remainder -= 1;
                } else {
                    b_string.append(0);
                }
                precision--;
            }
        }else{
            //finished
        }
        return b_string.toString();

    }
}
