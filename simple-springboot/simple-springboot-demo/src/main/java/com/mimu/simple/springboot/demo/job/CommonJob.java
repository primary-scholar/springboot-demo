package com.mimu.simple.springboot.demo.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CommonJob {

    @Scheduled(fixedDelay = 2000)
    public void info() throws InterruptedException {
        System.out.println("fixedDela job " + System.currentTimeMillis());
        while (!biggerThan9()) {
            //System.out.println("caculate");
        }
    }

    @Scheduled(fixedRate = 5000)
    public void print() throws InterruptedException {
        System.out.println("fixedRate job1 " + System.currentTimeMillis());
        while (!biggerThan9()) {
            //System.out.println("caculate");
        }
    }

    @Scheduled(fixedRate = 10000)
    public void sout() throws InterruptedException {
        System.out.println("fixedRate job2 " + System.currentTimeMillis());
        while (!biggerThan9()) {
            //System.out.println("caculate");
        }
    }

    private boolean biggerThan9() {
        return Math.random() * 10 > 9;
    }
}
