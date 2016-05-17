package com.example.u5778016.calculator.Parsing;

import android.widget.Toast;

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
// Tree with only one node
    public Tree(String data, Expression left){
        this.node = data;
        this.left = left;
    }
//different calculate function
    public double evaluate(){
        switch(node){
            case "+": return left.evaluate() + right.evaluate();
            case "-": return left.evaluate() - right.evaluate();
            case "*": return left.evaluate() * right.evaluate();
            case "/": return left.evaluate() / right.evaluate();
            case "%": return left.evaluate() % right.evaluate();
            case "^": return Math.pow(left.evaluate(), right.evaluate());
            case "√": return Math.sqrt(left.evaluate());
            case "s": return Math.sin(Math.toRadians(left.evaluate()));
            case "c": return Math.cos(Math.toRadians(left.evaluate()));
            case "t": return Math.tan(Math.toRadians(left.evaluate()));
            case "g": return Math.log10(left.evaluate());
            case "n": return Math.log(left.evaluate());
            case "&": return (int)left.evaluate() & (int)right.evaluate();
            case "|": return (int)left.evaluate() | (int)right.evaluate();
            case "~": return ~ (int)left.evaluate();
            case "⊕": return (int)left.evaluate() ^ (int)right.evaluate();
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
        //This is the way to identify whether the "(" and ")" is equal in expression
        if(postfix == "") {
            return new Invalid();
        } else {

            //Transform it to an ArrayList of tokens
            ArrayList<String> nodes = new ArrayList<>(Arrays.asList(postfix.split("#")));

            Stack tokens = new Stack();
            for (String current : nodes) {
                if ((NumberUtils.isNumber(current) || isHex(current)) && !current.isEmpty()) {
                    switch (base) {
                        case 10:
                            tokens.push(new Number(Double.parseDouble(current)));
                            break;
                        case 2:
                            tokens.push(new Number(BaseConversion.Binary_to_Decimal(current)));
                            break;
                        case 8:
                            tokens.push(new Number(BaseConversion.Octal_to_Decimal(current)));
                            break;
                        case 16:
                            tokens.push(new Number(BaseConversion.Hex_to_Decimal(current)));
                            break;
                        default:
                            tokens.push(new Number(Double.parseDouble(current)));
                    }
                } else if (!current.isEmpty()) {
                    tokens.size();
                    if (current.equals("-")) {
                        //identify the unary operator -
                        if (tokens.size() == 1) {
                            Expression a = (Expression) tokens.pop();
                            tokens.push(new Tree(current, a));
                        } else {
                            Expression b = (Expression) tokens.pop();
                            Expression a = (Expression) tokens.pop();
                            tokens.push(new Tree(current, a, b));
                        }
                    } else if (current.equals("+")) {
                        //identify the unary operator +
                        if (tokens.size() == 1) {
                            Expression a = (Expression) tokens.pop();
                            tokens.push(a);
                        } else {
                            Expression b = (Expression) tokens.pop();
                            Expression a = (Expression) tokens.pop();
                            tokens.push(new Tree(current, a, b));
                        }
                    } else if (current.equals("√") || current.equals("~") || current.equals("s") || current.equals("c") || current.equals("t") || current.equals("g") || current.equals("n")) {
                        if(tokens.size() == 1) {
                            Expression a = (Expression) tokens.pop();
                            tokens.push(new Tree(current, a));
                        } else
                            return new Invalid();
                    } else if(tokens.empty()) {
                        return new Invalid();
                    } else {
                        //wrong input such as "34*", one number and one operator
                        if (tokens.size() == 1) {
                            return new Invalid();
                        }
                        Expression b = (Expression) tokens.pop();
                        Expression a = (Expression) tokens.pop();
                        tokens.push(new Tree(current, a, b));
                    }
                }
            }
            return (Expression) tokens.pop();
        }
    }

    private static boolean isHex(String input){
        if(input.contains(".")){
            String part[] = input.split("\\.");
            return part.length == 2 && part[0].matches("-?[0-9A-F]+") && part[1].matches("-?[0-9A-F]+");

        }else{
            return input.matches("-?[0-9A-F]+");
        }

    }
}
