package com.example.u5778016.calculator.Parsing;

/**
 * Author: Dong Luo (u5319900)
 * File: Number.java
 * Description: Number class only has number
 */
public class Number extends Expression {
    double value;
    Number(double value){
        this.value = value;
    }

    public double evaluate(){
        return value;
    }
    public String show(){ return value+"";}


}
