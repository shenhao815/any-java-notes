package com.it.jvm;

import jdk.nashorn.internal.runtime.linker.Bootstrap;
import sun.misc.Launcher;

/**
 * @author CH
 * @description
 * @date 2020-8-21
 */
public class MyAppClassLoaderTest18 {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.system.class.loader"));

        System.out.println(MyAppClassLoaderTest18.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        /*
            如果想要出现如下结果，需要将CustomAppClassLoader放在AppClassLoader的classPath下，
            因为CustomAppClassLoader需要由AppClassLoader加载。其它的.class文件应当放在CustomAppClassLoader的classPath下，
            从而映证了修改java.system.class.loader确实有效。
            com.it.jvm.CustomAppClassLoader
            com.it.jvm.CustomAppClassLoader@15db9742
            com.it.jvm.CustomAppClassLoader@15db9742

            如果没有出现上述结果，可能是因为CACL(CustomAppClassLoader)的classPath没设置好，导致CACL由AppClassLoader加载，从而导致
            MyTest18也由AppClassLoader加载。
        * */
    }
}
