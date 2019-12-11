package com.mimu.simple.comn.zookeeper;


import com.netflix.config.DynamicStringProperty;
import org.junit.Test;

/**
 author: mimu
 date: 2019/12/11
 */
public class ZKConfigCenterTest {

    @Test
    public void info() throws InterruptedException {
        while (true){
            DynamicStringProperty abc = ZKConfigCenter.getString("abc", "");
            System.out.println(abc);
            Thread.sleep(1000);
        }
    }

}