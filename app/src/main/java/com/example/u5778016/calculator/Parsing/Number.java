package com.example.u5778016.calculator.Parsing;

/**
 * Created by u5319900 on 30/04/16.
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
