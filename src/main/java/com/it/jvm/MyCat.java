package com.it.jvm;

/**
 * @author CH
 * @description
 * @date 2020-8-20
 */
public class MyCat {
    public MyCat() {
        System.out.println("MyCat is loaded by: " + this.getClass().getClassLoader());

        System.out.println("from MyCat: " + MySample.class);
    }
}
