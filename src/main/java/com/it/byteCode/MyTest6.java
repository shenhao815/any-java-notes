package com.it.byteCode;
/*
    方法的动态分派：
    方法的动态分派涉及到一个重要概念：方法接收者。

    invokevirtual字节码指令的金矿查找流程:
    1:找到操作数栈顶的第一个元素，它所指向对象的实际的类型。对于本例来说，找到操作数栈顶的
    第一个元素所指向的对象的实际类型是Apple类型，如果在实际类型中找到了与常量池中的方法描述符以及
    方法名称等完全相同的方法，换句话说，在Apple类中查找有没有与Fruit类中test方法完成相同的方法描述
    符，如果找到了并且访问权限也校验通过了（public,private）,那就直接返回Apple类中的test方法的
    直接引用，换句话说，在真正运行期，将<com/it/byteCode/Fruit.tests>这样的符号引用转换成了
    Apple对象里的test方法的直接引用，如果找到就调用它，流程结束。
    2，如果没有找到，就沿着继承的层次体系从下往上再去进行不断的查找，查找的流程与上述的流程完成
    一致，如果在一个层次当中找到，那么就调用他，如何到顶层也没有找到，那么就抛出异常。

    比较：方法重载（overload）与方法重写（overwrite）
    方法重载是静态的，是编译期行为，方法重写是动态的，是运行期行为。

    最重要的区别，要看方法是谁来调用的。具体看MyTest5中和MyTest6中test的方法。
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
