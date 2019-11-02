package com.mimu.simple.java.utils.collection;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;


/**
 author: mimu
 date: 2019/11/1
 */

/**
 * LinkedHashSet 继承自 HashSet
 * LinkedHashSet 和 HashSet 都存储不可重复数据(重复即更新)都支持 增 add 删 remove 和 iterator 遍历
 * 但其输出顺序上 表现不一致 LinkedHashSet 输出顺序等于插入顺序，HashSet 无序
 *
 */
public class CollectionSetTest {

    /**
     * HashSet 内部使用 HashMap<E,Object> map; 进行数据的存储其中 value 使用 new Object() 进行填充
     * 特点如下
     * 1.容量不限制
     * 2.无序集合只能使用 iterator 进行循环遍历 iterator 返回 map.keySet().iterator()
     * 3.插入元素可为 null (唯一) 放在 HashMap 数组[0] 的位置
     * 4.线程不安全
     */
    @Test
    public void hashSetInfo() {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(null);
        hashSet.add(9);
        hashSet.add(13);
        hashSet.add(10);
        hashSet.add(14);
        hashSet.add(12);
        hashSet.add(13);
        hashSet.add(8);
        hashSet.add(null);
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        hashSet.remove(13);
        System.out.println(hashSet);
    }


    /**
     * LinkedHashSet 内部使用 LinkedHashMap<E,Object> map; 进行数据的存储其中 value 使用 new Object() 进行填充
     * 特点如下
     * 1.容量不限制
     * 2.有序集合只能使用 iterator 进行循环遍历 iterator 返回 map.keySet().iterator() 输出顺序和 输入顺序相同
     * 3.插入元素可为 null (唯一) 放在 HashMap 数组[0] 的位置
     * 4.线程不安全
     */
    @Test
    public void linkedHashSetInfo() {
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
        hashSet.add(null);
        hashSet.add(13);
        hashSet.add(10);
        hashSet.add(14);
        hashSet.add(13);
        hashSet.add(8);
        hashSet.add(null);
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        hashSet.remove(13);
        System.out.println(hashSet);
    }
}
