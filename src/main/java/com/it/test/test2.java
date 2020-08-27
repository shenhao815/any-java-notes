package com.it.test;

public class test2 {
    public static void main(String[] args) {
        System.out.println(test2.class.getClassLoader());

        test2 t2 = new test2();
        System.out.println(t2.getClass().getClassLoader());


    }
}