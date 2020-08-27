package com.it.jvm;

import jdk.nashorn.internal.runtime.linker.Bootstrap;
import sun.misc.Launcher;

/**
 * @author CH
 * @description
 * @date 2020-8-21
 */
public class MyTest18 {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.system.class.loader"));

        System.out.println(MyTest18.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
