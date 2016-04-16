package com.example.u5778016.calculator;

/**
 * Created by u5778016 on 13/04/16.
 */
public class reStack {
    private int maxsize;
    private char[] charnum;
    private int thefirstone;


    public reStack(int s){
        maxsize=s;
        charnum=new char[s];
        thefirstone = -1;
    }

    public reStack(){
        charnum=new char[maxsize];
        thefirstone = -1;
    }

    public void push(char c){
        charnum[++thefirstone] = c;
    }
    public char pop(){
        return charnum[thefirstone--];
    }
    public char peek(){
        return charnum[thefirstone];
    }

    public boolean isEmpty(){
        return thefirstone == -1;
    }

    public boolean isFull() {
        return thefirstone == (maxsize - 1);
    }

    public int size() {
        return thefirstone + 1;
    }

    public char get(int index) {
        return charnum[index];
    }

    public void display(String str) {
        System.out.print(str);
        System.out.print(" reStack (bottom-->top): ");
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i)+" ");
        }
        System.out.println();
    }
}
