package com.it.byteCode;
/*
    方法的动态分派：
    方法的动态分派涉及到一个重要概念：方法接收者。

    invokevirtual字节码指令的金矿查找流程
 */
public class MyTest6 {

    public static void main(String[] args) {
        Fruit apple = new Apple();
        Fruit orange = new Orange();

        apple.test(); // Apple
        orange.test(); // Orange

        apple = new Orange();
        apple.test(); // Orange
    }
}
class Fruit{
    public void test() {
        System.out.println("Fruit");
    }
}

class Apple extends Fruit {
    @Override
    public void test() {
        System.out.println("Apple");
    }
}

class Orange extends Fruit {
    @Override
    public void test() {
        System.out.println("Orange");
    }
}
