package com.mimu.simple.java.utils.collection;

import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 author: mimu
 date: 2019/11/2
 */

/**
 * ArrayBlockingQueue LinkedBlockingQueue ConcurrentLinkedQueue 都支持 增 删 查
 * 前两者使用锁进行并发控制 支持 阻塞的 增 删;
 * 后者使用 cas无锁化进行 并发控制 不支持阻塞方式
 * SynchronousQueue 是一个没有容量的队列(不可保存数据),用于生产者向 消费者传递数据
 */
public class CollectionQueueTest {

    /**
     * ArrayBlockingQueue 内部使用 Object[] items 存储数据
     * 使用单个锁 ReentrantLock lock, lock.newCondition() notEmpty notFull 进行 并发控制和阻塞与唤醒
     * 增 add(非满成功true 满异常) offer(非满成功true 满失败 false) put(非满成功true 满阻塞)
     * 删 poll(非空成功头元素 空null) take(非空成功头元素 空阻塞) 仅支持删除头元素 remove 仅支持iterator 的 remove
     * 查 peek(非空成功头元素 空null) 仅支持获取头元素
     *
     * 特点如下:
     * 容量固定
     * 元素先进先出(FIFO) 插入的顺序==输出的顺序 和 iterator 遍历顺序相同  iterator 返回的是 Object[] items 的转换
     * 插入的元素不可为 null,可以重复
     * 线程安全
     *
     * @throws InterruptedException
     */
    @Test
    public void arrayBlockingQueueInfo() throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        queue.add(13);
        queue.put(10);
        queue.offer(14);
        queue.add(13);
        queue.add(1);
        queue.put(0);
        queue.offer(4);
        queue.add(3);
        for (Integer aQueue : queue) {
            System.out.print(aQueue + " ");
        }
        System.out.println();
        System.out.println(queue);
        queue.poll();
        queue.take();
        queue.peek();
        System.out.println(queue);
    }

    /**
     * LinkedBlockingQueue 内部使用 Node<E> 单链表存储数据 可指定容量 或默认(Integer.MAX_VALUE)
     * static class Node<E> {
     *         E item;
     *
     *         Node<E> next;
     *
     *         Node(E x) { item = x; }
     *     }
     *
     * 使用两个锁 ReentrantLock takeLock(take poll 操作),ReentrantLock putLock (put offer 操作),
     * takeLock.newCondition() notEmpty,putLock.newCondition() notFull 对生产者和消费者进行区分(提高并发性能) 并发控制和阻塞与唤醒
     * 增 add(非满成功true 满异常) offer(非满成功true 满失败 false) put(非满成功true 满阻塞)
     * 删 poll(非空成功头元素 空null) take(非空成功头元素 空阻塞) 仅支持删除头元素
     * 查 peek(非空成功头元素 空null) 仅支持获取头元素
     *
     * 特点如下:
     * 容量固定
     * 元素先进先出(FIFO) 插入的顺序==输出的顺序 和 iterator 遍历顺序相同  iterator 返回的是 Node<E> 的转换
     * 插入的元素不可为 null,可以重复
     * 线程安全
     *
     * @throws InterruptedException
     */
    @Test
    public void linkedBlockingQueueInfo() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        queue.add(13);
        queue.put(10);
        queue.offer(14);
        queue.add(13);
        queue.add(1);
        queue.put(0);
        queue.offer(4);
        queue.add(3);
        for (Integer aQueue : queue) {
            System.out.print(aQueue + " ");
        }
        System.out.println();
        System.out.println(queue);
        queue.poll();
        queue.take();
        queue.peek();
        System.out.println(queue);
    }

    /**
     * ConcurrentLinkedQueue 使用 Node<E> 单链表 存储数据 是一个无界队列 不支持阻塞访问
     * private static class Node<E> {
     *         volatile E item;
     *         volatile Node<E> next;
     *
     *         /
     *         *
     *         *
     *         *
     *         /
     *     }
     *
     *使用 cas 进行数据的 增 删 查增
     *增 add(非满成功true(无界永远成功)) offer(非满成功true(无界永远成功))
     *删 poll(非空成功头元素 空null) 仅支持删除头元素
     *查 peek(非空成功头元素 空null) 仅支持获取头元素
     *
     *特点如下:
     *容量不固定(无界)
     *元素先进先出(FIFO) 插入的顺序==输出的顺序 和 iterator 遍历顺序相同  iterator 返回的是 Node<E> 的转换
     *插入的元素不可为 null,可以重复
     *线程安全
     */
    @Test
    public void concurrentLinkedQueueInfo() {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(13);
        queue.offer(14);
        queue.add(13);
        queue.add(1);
        queue.offer(4);
        queue.add(3);
        for (Integer aQueue : queue) {
            System.out.print(aQueue + " ");
        }
        System.out.println();
        System.out.println(queue);
        queue.poll();
        queue.peek();
        System.out.println(queue);
    }

    /**
     * SynchronousQueue 是一个没有容量的队列(不可保存数据),用于生产者向 消费者传递数据
     */
    @Test
    public void synchronousQueueInfo() throws IOException {
        int i = 13;
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    boolean add = queue.add(i);
                    System.out.println("add " + add);
                } catch (InterruptedException e) {
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println("queue " + queue);
                } catch (InterruptedException e) {
                }

            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    Integer poll = queue.poll();
                    System.out.println("poll " + poll);
                } catch (InterruptedException e) {
                }

            }
        }).start();
        System.in.read();
    }

}
