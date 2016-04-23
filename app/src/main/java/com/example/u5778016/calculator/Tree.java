package com.example.u5778016.calculator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by u5319900 on 23/04/16.
 */
public class Tree {
    String node;
    Tree left,right;

    public Tree(String data){
        this.node = data;
        left = new Tree();
        right = new Tree();
    }

    public Tree(){
        this.node = null;
    }

    public double evaluate(){
        switch(node){
            case "+": return left.evaluate() + right.evaluate();
            case "-": return left.evaluate() - right.evaluate();
            case "*": return left.evaluate() * right.evaluate();
            case "/": return left.evaluate() / right.evaluate();
            case "%": return left.evaluate() % right.evaluate();
            case "^": return Math.pow(left.evaluate(), right.evaluate());
            default : return Double.parseDouble(node);
        }
    }

    public static Tree generate(String infix){
        Transforming t = new Transforming(infix);
        String postfix = t.doTransform();
        ArrayList<String> nodes = new ArrayList<>(Arrays.asList(postfix.split("#")));
        return new Tree();
    }
}
