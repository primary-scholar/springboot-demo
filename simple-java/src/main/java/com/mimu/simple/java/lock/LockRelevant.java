package com.mimu.simple.java.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 author: mimu
 date: 2019/12/6
 */
public class LockRelevant {

    /**
     * 方法中使用 锁 仅能保证 在某一时刻 方法仅有一个 线程在执行
     * ReentrantLock 为可重入锁，意味着 获取到锁的 线程可以 多次在 某个方法中加锁解锁，
     * 其他为获取到锁的线程只能等待
     */
    static class InnerLock1 {
        private final ReentrantLock lock;

        public InnerLock1(boolean fair) {
            this.lock = new ReentrantLock(fair);
        }

        public void printA() {
            ReentrantLock lock = this.lock;
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " A");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printB() {
            ReentrantLock lock = this.lock;
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " B");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * printA() printB() 交替执行，可以仿照 生产者和消费者 模式进行
     * 交替 需要两个 condition 对象进行 阻塞与唤醒 同时还需一个 标识位 让阻塞陷入循环
     */
    static class InnerLock2 {
        private final ReentrantLock lock;
        private Condition conditionA;
        private Condition conditionB;
        private volatile boolean alternate;

        public InnerLock2(boolean fair) {
            this.lock = new ReentrantLock(fair);
            this.conditionA = this.lock.newCondition();
            this.conditionB = this.lock.newCondition();
            this.alternate = true;
        }

        public void printA() {
            ReentrantLock lock = this.lock;
            lock.lock();
            try {
                while (alternate) {
                    conditionA.await();
                }
                System.out.println(Thread.currentThread() + " A");
                alternate = true;
                conditionB.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printB() {
            ReentrantLock lock = this.lock;
            lock.lock();
            try {
                while (!alternate) {
                    conditionB.await();
                }
                System.out.println(Thread.currentThread() + " B");
                alternate = false;
                conditionA.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
