package com.mimu.simple.java.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

/**
 * author: mimu
 * date: 2019/9/23
 */

/**
 * LinkedList 和 ArrayList 表现功能一致，支持 增 add 删 remove 改 set 查 get
 * 都可以 存放 null
 */
public class CollectionListTest {

    /**
     *
     */
    @Test
    public void arrayListInfo() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add(13);
        arrayList.add(1, 13);
        arrayList.add(10);
        arrayList.add(14);
        arrayList.set(3, 11);
        System.out.println(arrayList);

        /**
         * 多个 重复元素 删除第一个
         */
        arrayList.remove((Integer) 13);
        arrayList.remove(3);
        System.out.println(arrayList);
    }

    @Test
    public void linkedListInfo() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add(13);
        linkedList.add(1, 13);
        linkedList.add(10);
        linkedList.add(14);
        linkedList.set(3, 11);

        /**
         * 多个 重复元素 删除第一个
         */
        linkedList.remove((Integer) 13);
        linkedList.remove(3);
        System.out.println(linkedList);
    }
}
