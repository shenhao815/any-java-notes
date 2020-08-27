package com.it.jvm;

/**
 * @author CH
 * @description
 * @date 2020-8-20
 */
public class MySample {
    public MySample() throws IllegalAccessException, InstantiationException {
        System.out.println("MySample is loaded by: "+ this.getClass().getClassLoader());

        MyCat.class.newInstance();
    }
}
