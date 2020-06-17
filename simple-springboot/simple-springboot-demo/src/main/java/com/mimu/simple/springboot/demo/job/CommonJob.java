package com.mimu.simple.springboot.demo.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CommonJob {

    /*@Scheduled(fixedDelay = 10)
    public void info() throws InterruptedException {
        System.out.println("fixedDela job " + System.currentTimeMillis());
        while (!biggerThan9()) {
            //System.out.println("caculate");
        }
    }*/

    @Scheduled(fixedRate = 200)
    public void print() throws InterruptedException {
        System.out.println("fixedRate job " + System.currentTimeMillis());
        while (!biggerThan9()) {
            //System.out.println("caculate");
        }
    }

    private boolean biggerThan9() {
        return Math.random() * 10 > 9.999999;
    }
}
