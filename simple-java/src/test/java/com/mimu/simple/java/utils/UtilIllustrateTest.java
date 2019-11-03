package com.mimu.simple.java.utils;

/**
 author: mimu
 date: 2019/11/1
 */

/**
 * 在 java.util 包中提供两种类型的 数据存储结构：1 collection;2 Map
 * 1 collection 提供了保存一组元素的存储结构 旗下包含:
 *
 * 1.1 List(有序(输出顺序等于插入顺序)可重复);
 * 1.2 Set(部分有序(Comparable确定顺序或输出顺序等于插入顺序或无序)且不可重复(equals确定重复));
 * 1.3 Queue(一种特殊的List结构);
 * 1.4 Deque(双端Queue);
 *
 * 1.1 List 常用结构有两种 1.1.1 ArrayList;1.1.2 LinkedList
 * 1.1.1 ArrayList 有序可重复(仅支持尾插)
 * 1.1.2 LinkedList 有序可重复(支持头插和尾插) 实现了Queue接口
 *
 * 1.2 Set 常用的结构有两类 1.2.1 SortedSet(有序不可重复);1.2.2 Set(可有序可无序且不可重复)
 * 1.2.1 SortedSet 常用结构有 1.2.1.1 TreeSet
 * 1.2.1.1 TreeSet(有序不可重复Comparable确定顺序)
 * 1.2.2 Set 常用结构有 1.2.2.1 LinkedHashSet;1.2.2.2 HashSet
 * 1.2.2.1 LinkedHashSet(有序不可重复输出顺序等于插入顺序);
 * 1.2.2.2 HashSet(无序不可重复)
 *
 *1.3 Queue在jdk1.5 后添加 常用结构 1.3.1 ArrayBlockingQueue; 1.3.2 LinkedBlockingQueue; 1.3.3 SynchronousQueue; 1.3.4 ConcurrentLinkedQueue;
 * 1.3.1 ArrayBlockingQueue(有界有序可重复)
 * 1.3.2 LinkedBlockingQueue(有界有序可重复)
 * 1.3.3 SynchronousQueue(无容量仅支持数据转移)
 * 1.3.4 ConcurrentLinkedQueue(无界有序可重复不支持阻塞)
 *
 *
 *
 * 2 Map 提供了保存一组<key,value>数据的存储结构 key 不可重复 旗下包含: 可提供 keySet collectionsValue <key,value> 三种访问方式
 * 2.1 Map(有序或无序 key不可重复) 常用结构 2.1.1 HashMap 2.1.2 HashTable
 * 2.2 SortedMap(有序 key不可重复) 常用结构 2.2.1 TreeMap
 * 2.3 ConcurrentMap(无序 key不可重复 线程安全)  常用结构 2.3.1 ConcurrentHashMap
 *
 * 2.1.1 HashMap(无序 key 可 null 不可重复)
 * 2.1.2 HashTable(HashMap 的线程安全版)
 * 2.2.1 TreeMap(有序不可重复Comparable确定顺序)
 * 2.3.1 ConcurrentHashMap()
 */
public class UtilIllustrateTest {
}
