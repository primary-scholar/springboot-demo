package com.mimu.simple.comn.servletcontainer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

/**
 author: mimu
 date: 2020/1/14
 */
public class JettyTest {
    public static void main(String[] args) throws Exception {
        int port = 9090;
        Server server = new Server(port);
        ServerConnector connector = new ServerConnector(server,1,1);
        //connector.setPort(port);
        server.addConnector(connector);
        server.start();
    }
}
