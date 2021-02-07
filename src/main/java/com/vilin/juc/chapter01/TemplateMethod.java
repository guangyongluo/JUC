package com.vilin.juc.chapter01;

public class TemplateMethod {

    public final void print(String message){
        System.out.println("#############");
        wrapPrint(message);
        System.out.println("#############");
    }

    protected void wrapPrint(String message){

    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod(){
            protected void wrapPrint(String message){
                System.out.println("*"+message+"*");
            }
        };

        t1.print("Hello Thread");

        TemplateMethod t2 = new TemplateMethod(){
            protected void wrapPrint(String message){
                System.out.println("+"+message+"+");
            }
        };

        t2.print("Hello Thread");
    }
}
