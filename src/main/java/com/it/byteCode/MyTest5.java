package com.it.byteCode;

/*
    方法的静态分派。
    Grandpa g1 = new Father();
    以上代码，g1的静态类型（声明类型）是Grandpa,而g1的实际类型（真正指向的类型）是Father.
    我们可以得出这样一个结论：变量的静态类型是不会发生变化的，而变量的实际类型则是可以发生变化的（多态的一种体现），实际类型是在
    运行期方可确定。

    方法重载，是一种静态的行为。所谓的静态的行为是指，在执行方法重载的时候，调用这个方法，jvm唯一判断的依据就是根据这个方法本身他接受的参数的静态类型
    来去决定要调用哪个版本的方法，而不是根据这个方法参数的实际类型去决定的。
    这种静态行为，在编译期就已经完全确定。
 */
public class MyTest5 {

    public static void main(String[] args) {
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();

        MyTest5 myTest5 = new MyTest5();
        myTest5.test(g1);
        myTest5.test(g2);//方法重载
    }

    // 方法重载，是一种静态的行为。
    public void test(Grandpa grandpa) {
        System.out.println("grandpa");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(Son son) {
        System.out.println("son");
    }
}

class Grandpa {

}

class Father extends Grandpa {

}

class Son extends Father {

}
