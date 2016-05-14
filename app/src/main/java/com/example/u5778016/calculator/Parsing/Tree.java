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

    public String show(){
        return "(" + left.show() + "[" + node + "]" + right.show() + ")";
    }

    public static Expression generate(String infix, int base){
        //Transform the string to postfix
        Transforming t = new Transforming(infix);
        String postfix = t.doTransform();

        //Transform it to an ArrayList of tokens
        ArrayList<String> nodes = new ArrayList<>(Arrays.asList(postfix.split("#")));

        Stack tokens = new Stack();
        for(String current:nodes){
            if(NumberUtils.isNumber(current) && !current.isEmpty()){
                switch (base) {
                    case 10:
                        tokens.push(new Number(Double.parseDouble(current)));
                        break;
                    case 2:
                        tokens.push(new Number(BaseConversion.Binary_to_Decimal(current)));
                        break;
                    case 8:
                        tokens.push(new Number((double) Integer.parseInt(current, 8)));
                        break;
                    case 16:
                        tokens.push(new Number((double) Integer.parseInt(current, 16)));
                        break;
                    default:
                        tokens.push(new Number(Double.parseDouble(current)));
                }
            }else if(!current.isEmpty()){
                    Expression b = (Expression) tokens.pop();
                    Expression a = (Expression) tokens.pop();
                    tokens.push(new Tree(current, a , b));
                //tokens.push(new Tree(current, a , b));
            }
        }
        return (Expression) tokens.pop();
    }
}
