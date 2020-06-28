package arrayListLinkList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author CH
 * @description
 * @date 2020-6-28
 */


public class TestSpeed_List {
    //测试arrayList插入速度和LinkedList插入速度
    //插入数据20万条
    public static void testArrayList() {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 20000; i++) {
            list.add(i);
        }
        long startTime = System.nanoTime();
        //for (int i = 0; i < 200000; i++) {
            list.add(100, 100);
        //}
        System.out.println(list.size());
        long endTime = System.nanoTime();
        long time = (endTime - startTime);
        System.err.println("arrayList:运行时间:" + time + "ms");
    }

    //插入数据20万条
    public static void testLinkedList() {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 20000; i++) {
            list.add(i);
        }
        long startTime = System.nanoTime();
        //for (int i = 0; i < 200000; i++) {
            list.add(100, 100);
        //}
        System.out.println(list.size());
        long endTime = System.nanoTime();
        long time = (endTime - startTime);
        System.err.println("linkedList:运行时间:" + time + "ms");
    }

    public static void main(String[] args) {
        TestSpeed_List.testArrayList();
        TestSpeed_List.testLinkedList();
    }
}
