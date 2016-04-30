package com.example.u5778016.calculator.Parsing;

import com.example.u5778016.calculator.Transforming;

import java.util.*;
import org.apache.commons.lang3.math.NumberUtils;

/**googl
 * Created by u5319900 on 23/04/16.
 */
public class Tree extends Expression {
    String node;
    Expression left,right;

    public Tree(String data, Expression left, Expression right){
        this.node = data;
        this.left = left;
        this.right = right;
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

    public static Expression generate(String infix){
        //Transform the string to postfix
        Transforming t = new Transforming(infix);
        String postfix = t.doTransform();

        //Transform it to an ArrayList of tokens
        ArrayList<String> nodes = new ArrayList<>(Arrays.asList(postfix.split("#")));

        Stack tokens = new Stack();
        int index = 0;
        while(index < nodes.size()){
            String current = nodes.get(index);
            if(NumberUtils.isNumber(current)){
                tokens.push(current);
            }else{
                //it is a operator :)
            }
        }
        return new Number(0);
    }
}
