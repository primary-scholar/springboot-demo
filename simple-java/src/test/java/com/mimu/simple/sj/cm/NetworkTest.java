package com.mimu.simple.sj.cm;

import java.net.InetSocketAddress;

/**
 * author: mimu
 * date: 2019/7/12
 */
public class NetworkTest {
    public static void main(String[] args) {
        while (true){
            InetSocketAddress address = new InetSocketAddress("localhost",9090);
            System.out.println(address.getAddress());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
