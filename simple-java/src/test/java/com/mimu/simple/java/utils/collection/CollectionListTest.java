package com.mimu.simple.java.utils.collection;

import org.junit.Test;

import java.util.*;

/**
 * author: mimu
 * date: 2019/9/23
 */

/**
 * LinkedList 和 ArrayList 表现功能一致，支持 增 add 删 remove 改 set 查 get
 * 都可以 存放 null(可多个)，和重复数据，插入的顺序==输出的顺序，线程不安全
 */
public class CollectionListTest {

    /**
     * ArrayList 使用数组 transient Object[] elementData 保存数据，
     * 特点如下：
     * <p>
     * 容量不固定，随着容量的增加而动态扩容(1.5倍)（阈值基本不会达到）
     * 有序集合（插入的顺序==输出的顺序）和 iterator 遍历顺序相同  iterator 返回的是 Object[] elementData 的转换
     * 插入的元素可以为null,可以重复
     * 增删改查效率更高（相对于LinkedList来说）
     * 线程不安全
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
        arrayList.add(13);
        arrayList.add(null);
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        System.out.println(arrayList);

        /**
         * 多个 重复元素 删除第一个
         */
        arrayList.remove((Integer) 13);
        arrayList.remove(3);
        System.out.println(arrayList);
    }

    /**
     * LinkedList 使用 双向链表 的方式 保存数据 支持头插法和尾插法两种 插入方式
     * <p>
     *     private static class Node<E> {
     *         E item;
     *         Node<E> next;
     *         Node<E> prev;
     *
     *         Node(Node<E> prev, E element, Node<E> next) {
     *             this.item = element;
     *             this.next = next;
     *             this.prev = prev;
     *         }
     *     }
     *
     * <p>
     * 容量没有限制
     * 有序集合（插入的顺序==输出的顺序）和 iterator 遍历顺序相同 iterator 返回的是 Node 双向链表 的转换
     * 插入的元素可以为null，可以重复，支持头插和尾插 两种插入顺序
     * 增删改查效率低（相对于ArrayList来说）
     * 线程不安全
     */
    @Test
    public void linkedListInfo() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add(13);
        linkedList.add(1, 13);
        linkedList.add(10);
        linkedList.add(14);
        linkedList.set(3, 11);
        linkedList.add(13);
        linkedList.addFirst(null);
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        System.out.println(linkedList);
        /**
         * 多个 重复元素 删除第一个
         */
        linkedList.remove((Integer) 13);
        linkedList.remove(3);
        System.out.println(linkedList);
    }
}
