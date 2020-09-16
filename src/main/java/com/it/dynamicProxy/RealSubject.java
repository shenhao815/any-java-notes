package com.it.dynamicProxy;

/**
 * @author CH
 * @description
 * @date 2020-9-16
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("Form real subject");
    }
}
