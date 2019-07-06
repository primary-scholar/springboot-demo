package com.mimu.simple.sg;

import org.junit.Test;

import java.io.IOException;

/**
 * author: mimu
 * date: 2019/1/9
 */

public class HelloWorldTest {

    @Test
    public void info() throws IOException {
        HelloWorldServer server = new HelloWorldServer(10010);
        HelloWorldClient client = new HelloWorldClient("127.0.0.1",10010);
        server.start();
        client.greet("haha");
        server.stop();
    }

}
