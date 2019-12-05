package com.mimu.simple.java.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private ReentrantLock lock = new ReentrantLock(true);

    @Test
    public void print() {
        try {
            lock.lock();
            System.out.println("aaa");
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }
}
