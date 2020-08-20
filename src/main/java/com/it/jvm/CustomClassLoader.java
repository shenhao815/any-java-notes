package com.it.jvm;

import java.io.*;
import java.util.regex.Matcher;

/**
 * @author CH
 * @description
 * @date 2020-8-19
 */
public class CustomClassLoader extends ClassLoader {

    private final String fileExtension = ".class";
    private String classLoaderName;
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public CustomClassLoader(String classLoaderName) {
        super(); // 将系统/应用类加载器作为该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public CustomClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent); // 显式指定该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    @Override
    public String toString() {
        return "[" + classLoaderName + "]";
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("class loader name: "+ this.classLoaderName);
        byte[] data = this.loadClassData(className);
        return this.defineClass(className,data,0,data.length);
    }

    private byte[] loadClassData(String className) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try {
            className = className.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void test() throws Exception {
        /*CustomClassLoader loader1 = new CustomClassLoader("loader1");
        com.it.test(loader1);*/
        CustomClassLoader loader1 = new CustomClassLoader("loader1"); // new CustomClassLoader(null, "loader1");
        loader1.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class clazz = loader1.loadClass("com.it.controlCPU.ControlCPU");
        System.out.println("class: "+ clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println("定义类加载器： "+ clazz.getClassLoader());

        /*
         *  new CustomClassLoader(loader1,"loader2");
         *  此时，clazz和clazz2相同，因为此时命名空间相同
         * */
        CustomClassLoader loader2 = new CustomClassLoader("loader2");
        loader2.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class clazz2 = loader2.loadClass("com.it.controlCPU.ControlCPU");
        System.out.println("class: "+ clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2);
        System.out.println("定义类加载器： "+ clazz2.getClassLoader());
        /*
        * 打印结果：
        *   [Loaded com.it.controlCPU.ControlCPU from __JVM_DefineClass__]
            class: 1956725890
            com.it.controlCPU.ControlCPU@1540e19d
            定义类加载器： [loader1]
            class loader name: loader2
            [Loaded com.it.controlCPU.ControlCPU from __JVM_DefineClass__]
            class: 2133927002
            com.it.controlCPU.ControlCPU@6d6f6e28
            定义类加载器： [loader2]
        *
        * 导致此结果的原因： 命名空间不同
        * */
    }

    private static void unloadClassTest() throws Exception {
        CustomClassLoader loader1 = new CustomClassLoader("loader1"); // new CustomClassLoader(null, "loader1");
        loader1.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class clazz = loader1.loadClass("com.it.controlCPU.ControlCPU");
        System.out.println("class: "+ clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println("定义类加载器： "+ clazz.getClassLoader());

        clazz = null;
        object = null;
        loader1 = null;
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        //test();
        unloadClassTest();
    }

}
