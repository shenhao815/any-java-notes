package com.it.jvm;

/**
 * @author CH
 * @description
 * @date 2020-8-20
 */
public class LoadTest {

    public static void main(String[] args) throws  Exception{
        CustomClassLoader loader1 = new CustomClassLoader("loader1");
        loader1.setPath("C:\\Users\\Administrator\\Desktop\\");

        Class clazz = loader1.loadClass("com.it.jvm.MySample");
        System.out.println(clazz.hashCode());

        // 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
        // 因此不会实例化MyCat对象，即没有对MyCat进行主动使用
        // 但是，这是会不会加载MyCat？答案是不一定！因为： 类的加载器并不需要等到某个类被”首次主支使用“时再加载它
        // 参考《jvm学习》1.5 类的加载器
        Object object = clazz.newInstance();
    }
}
