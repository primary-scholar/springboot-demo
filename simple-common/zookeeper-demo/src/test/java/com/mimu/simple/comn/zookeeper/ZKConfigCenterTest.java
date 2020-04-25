package com.mimu.simple.comn.zookeeper;


import com.netflix.config.DynamicStringProperty;
import org.junit.Test;

import java.io.PrintWriter;

/**
 author: mimu
 date: 2019/12/11
 */
public class ZKConfigCenterTest {

    public String value = ZKConfigCenter.getString("abc", "", new Runnable() {
        @Override
        public void run() {
            value = ZKConfigCenter.getString("abc", "").get();
        }
    }).get();


    @Test
    public void info() throws InterruptedException {
        DynamicStringProperty abc = ZKConfigCenter.getString("abc", "");
        while (true) {
            System.out.println(abc.get());
            Thread.sleep(1000);
        }
    }

    @Test
    public void info1() throws InterruptedException {
        while (true) {
            System.out.println(value);
            Thread.sleep(1000);
        }
    }

}