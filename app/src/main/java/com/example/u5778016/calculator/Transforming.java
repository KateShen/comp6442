package com.example.u5778016.calculator;

import java.util.Stack;

/**
 * Created by u5778016 on 13/04/16.
 */
public class Transforming {
    private reStack stackfortrans;
    private String input;
    private String output="";

    public Transforming(String input){
        this.input=input;
        int size=input.length();
        stackfortrans=new reStack(size);
    }

    public String doTransform(){

        for (int i=0;i<input.length();i++){
            char specificchar=input.charAt(i);
            switch (specificchar){
                case '+':
                case '-':
                    compareandadd(specificchar,1);
                    break;
                case '*':
                case '/':
                    compareandadd(specificchar,2);
                    break;
                case '(':
                    stackfortrans.push(specificchar);
                    break;
                case ')':
                    getLast(specificchar);
                    break;
                default:
                    output=output+specificchar;
                    break;

            }
        }

        while (!stackfortrans.isEmpty()){
            output=output+stackfortrans.pop();
        }

        return output;

    }


    public void compareandadd(char inputstr,int status){
        while (!stackfortrans.isEmpty()){
            char inlist=stackfortrans.pop();
            if (inlist=='('){
                stackfortrans.push(inlist);
                break;
            }else {
                int status2;
                if (inlist=='+' ||inlist=='-'){
                    status2=1;
                }else {
                    status2=2;
                }
                if (status2<status){
                    stackfortrans.push(inlist);
                    break;
                }else {
                    output=output+inlist;
                }
            }
        }
        stackfortrans.push(inputstr);
    }

   public void getLast(char ch){
       while (! stackfortrans.isEmpty()){
           char inlist=stackfortrans.pop();
           if (inlist=='('){
               break;
           }else {
               output=output+inlist;
           }
       }

   }
}
