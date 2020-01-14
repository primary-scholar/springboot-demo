package com.mimu.simple.comn.servletcontainer;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 author: mimu
 date: 2020/1/14
 */
public class TomcatTest {
    public static void main(String[] args) throws LifecycleException {
        int port = 9090;
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(port);
        tomcat.setPort(port);
        tomcat.setConnector(connector);
        tomcat.start();
        tomcat.getServer().await();
    }
}
