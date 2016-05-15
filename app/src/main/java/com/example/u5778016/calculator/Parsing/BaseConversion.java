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
            //System.out.println("whole = " + whole);
            fraction = b_part[1];
            //System.out.println("fraction = " + fraction);

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
                    b_string.append((int) remainder);
                    remainder %= 1;
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

    public static double Octal_to_Decimal(String o_string) {
        double d_number = 0;
        String whole,fraction;
        if(o_string.contains(".")) {
            String b_part[] = o_string.split("\\.");
            whole = b_part[0];
            //System.out.println("whole = " + whole);
            fraction = b_part[1];
            //System.out.println("fraction = " + fraction);

            for (int i = 0; i < fraction.length(); i++) {
                d_number += Character.getNumericValue(fraction.charAt(i)) * Math.pow(8, -i - 1);
            }
        }else{
            whole = o_string;
        }

        for (int i = whole.length(); i > 0; i--) {
            d_number += Character.getNumericValue(whole.charAt(i - 1)) * Math.pow(8, whole.length() - i);
        }

        return d_number;
    }

    public static String Decimal_to_Octal(double d_number) {
        double whole = Math.floor(d_number);
        double fraction = d_number - whole;
        StringBuilder o_string = new StringBuilder();
        o_string.append(Integer.toOctalString((int) whole));

        if (fraction > 0) {
            o_string.append(".");
            double remainder = fraction;
            int precision = 4;
            while (remainder > 0 && precision > 0) {
                remainder *= 8;
                if (remainder >= 1) {
                    o_string.append((int) remainder);
                    remainder %= 1;
                } else {
                    o_string.append(0);
                }
                precision--;
            }
        }else{
            //finished
        }
        return o_string.toString();

    }

    public static double Hex_to_Decimal(String h_string) {
        double d_number = 0;
        String whole,fraction;
        if(h_string.contains(".")) {
            String b_part[] = h_string.split("\\.");
            whole = b_part[0];
            //System.out.println("whole = " + whole);
            fraction = b_part[1];
            //System.out.println("fraction = " + fraction);

            for (int i = 0; i < fraction.length(); i++) {
                d_number += Integer.parseInt(Character.toString(fraction.charAt(i)), 16) * Math.pow(16, -i - 1);
            }
        }else{
            whole = h_string;
        }

        for (int i = whole.length(); i > 0; i--) {
            d_number += Integer.parseInt(Character.toString(whole.charAt(i - 1)), 16) * Math.pow(16, whole.length() - i);
        }

        return d_number;
    }

    public static String Decimal_to_Hex(double d_number) {
        double whole = Math.floor(d_number);
        double fraction = d_number - whole;
        StringBuilder h_string = new StringBuilder();
        h_string.append(Integer.toHexString((int) whole).toUpperCase());

        if (fraction > 0) {
            h_string.append(".");
            double remainder = fraction;
            int precision = 4;
            while (remainder > 0 && precision > 0) {
                remainder *= 16;
                if (remainder >= 1 && remainder < 10) {
                    h_string.append((int) remainder);
                } else if (remainder >= 10){
                    switch ((int) remainder){
                        case 10 : h_string.append("A");
                            break;
                        case 11 : h_string.append("B");
                            break;
                        case 12 : h_string.append("C");
                            break;
                        case 13 : h_string.append("D");
                            break;
                        case 14 : h_string.append("E");
                            break;
                        case 15 : h_string.append("F");
                            break;
                    }
                } else {
                    h_string.append(0);
                }
                remainder %= 1;
                precision--;
            }
        }else{
            //finished
        }
        return h_string.toString();

    }
}