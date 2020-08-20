package com.it.arrayListLinkList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author CH
 * @description
 * @date 2020-6-24
 */
public class Test {

    /*
    * 对于给最末位add，LinkedList比ArrayList要快，因为此时LL不需要遍历集合
    * 对于给指定位置add、set、remove,LL比AL要慢，因为要对LL做for循环，性能很差
    * 当然，给AL做add时，需要给AL做复制，如果index越靠前，效率越差，因为复制的越多，remove同理
    * */
    public static void main(String... args) {
        final int MAX_VAL = 100000;
        List<Integer> linkedList = new LinkedList<Integer>();
        List<Integer> arrayList = new ArrayList<Integer>();
        for(int i = 0; i < MAX_VAL; i++) {
            linkedList.add(i);
            arrayList.add(i);
        }
        long time1 = System.currentTimeMillis();
        for(int i = 0; i < MAX_VAL; i++) {
            arrayList.set(100,i);
        }
        System.out.println("AL time: " + (System.currentTimeMillis() - time1));
        long time2 = System.currentTimeMillis();
        for(int i = 0; i < MAX_VAL; i++) {
            linkedList.set(100,i);
        }
        System.out.println("LL time: " + (System.currentTimeMillis() - time2));

    }
}
