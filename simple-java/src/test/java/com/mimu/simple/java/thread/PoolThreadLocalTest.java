package com.mimu.simple.java.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PoolThreadLocalTest {

    private static ThreadLocal<AtomicInteger> sequencer = ThreadLocal.withInitial(() -> new AtomicInteger(0));

    static class TaskFirst implements Runnable {

        @Override
        public void run() {
            AtomicInteger s = sequencer.get();
            int initial = s.getAndIncrement();
            System.out.println(initial);
        }
    }

    static class TaskSecond implements Runnable {
        @Override
        public void run() {
            AtomicInteger s = sequencer.get();
            int initial = s.getAndIncrement();
            System.out.println(initial);
            sequencer.remove();
        }
    }

    /**
     * in the task first test situation: the print result in run is not the initialized value
     * which we initialized in the ThreadLocal
     * here print is 0 1 2 not 0 0 0 why!!
     * because here we define a executorService with only one thread,
     * so the second task and the third task will reuse the first thread to execute the task
     * so in the second and third task will get the initialized value which it initialized in the first thread
     * so the result print is 0 1 2
     */
    @Test
    public void info1() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new TaskFirst());
        executor.execute(new TaskFirst());
        executor.execute(new TaskFirst());
        executor.shutdown();
    }

    /**
     * in the task second test situation: the only different point is that we invoke the remove() method
     * in the task logic so the result is we expect 0 0 0
     */
    @Test
    public void info() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new TaskSecond());
        executor.execute(new TaskSecond());
        executor.execute(new TaskSecond());
        executor.shutdown();
    }

}
