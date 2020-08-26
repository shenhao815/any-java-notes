package com.it.jvm;

import java.io.*;
import java.util.regex.Matcher;

/**
 * @author CH
 * @description
 * @date 2020-8-19
 */
public class CustomAppClassLoader extends ClassLoader {

    private final String fileExtension = ".class";
    private String classLoaderName;
    private String path = "C:\\Users\\Neo\\Desktop\\";


    public CustomAppClassLoader(){}

    public CustomAppClassLoader(ClassLoader parent) {
        super(parent); // 显式指定该类加载器的父加载器
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


}
