package com.example.u5778016.calculator;

import android.app.Dialog;
import android.widget.Toast;

import com.example.u5778016.calculator.Parsing.Invalid;


/**
 * Created by u5778016 on 13/04/16.
 */
public class Transforming {
    private ReStack stackfortrans;
    private String input;
    private String output = "";

    public Transforming(String input){
        this.input = input;
        int size = input.length();
        stackfortrans = new ReStack(size);
    }

    public String doTransform(){
        int c=0;
        int d=0;
        for (int j=0;j<input.length();j++){

            char specificjudge=input.charAt(j);
            if (specificjudge=='('){
                c++;
            }else if (specificjudge==')'){
                d++;
            }
        }

        if (c==d){
           //Toast.makeText(this,"a",Toast.LENGTH_LONG).show();
            //JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
            boolean ischar = false;
            for (int i = 0; i < input.length(); i++) {
                char specificchar = input.charAt(i);
                if (specificchar == ' ') {
                    continue;
                } else if (specificchar == '-' && (output.equals("") || ischar)) {
                    output = output + "#" + specificchar;
                    ischar = false;
                    continue;
                }
                switch (specificchar) {
                    case '+':
                    case '-':
                    case '|':
                    case '⊕':
                        compareandadd(specificchar, 1);
                        ischar = true;
                        break;
                    case '*':
                    case '/':
                    case '%':
                    case '&':
                        compareandadd(specificchar, 2);
                        ischar = true;
                        break;
                    case '^':
                        compareandadd(specificchar, 3);
                        ischar = true;
                        break;
                    case '~':
                    case 's':
                    case 'c':
                    case 't':
                    case 'g':
                    case 'n':
                        compareandadd(specificchar, 4);
                        ischar = true;
                        break;
                    case '(':
                        stackfortrans.push(specificchar);
                        ischar = true;
                        break;
                    case ')':
                        getLast(specificchar);
                        ischar = true;
                        break;
                    default:
                        if (!ischar) {
                            output = output + specificchar;
                        } else {
                            output = output + "#" + specificchar;
                        }
                        ischar = false;
                        break;
                }
            }while (!stackfortrans.isEmpty()){
                output=output + "#" + stackfortrans.pop();
            }
            return output;
        } else {
            return "";
        }
    }



    public void compareandadd(char inputstr,int status){
        while (!stackfortrans.isEmpty()){
            char inlist = stackfortrans.pop();
            if (inlist == '('){
                stackfortrans.push(inlist);
                break;
            } else {
                int status2 = 0;
                if (inlist == '+' || inlist == '-' || inlist == '|' || inlist == '⊕'){
                    status2 = 1;
                } else if(inlist == '*' || inlist == '/' || inlist == '%' || inlist == '&') {
                    status2 = 2;
                } else if (inlist == '^') {
                    status2 = 3;
                } else if(inlist == '~'||inlist=='s'||inlist=='c'||inlist=='t'||inlist=='g'||inlist=='n') {
                    status2 = 4;
                }
                if (status2 < status){
                    stackfortrans.push(inlist);
                    break;
                }else {
                    output = output + "#" + inlist;
                }
            }
        }
        stackfortrans.push(inputstr);
    }

   public void getLast(char ch){
       while (! stackfortrans.isEmpty()){
           char inlist = stackfortrans.pop();
           if (inlist == '('){
               break;
           }else {
               output=output + "#" + inlist;
           }
       }

   }
}
