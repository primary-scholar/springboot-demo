package com.mimu.simple.comn.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 author: mimu
 date: 2020/1/14
 */
public class TomcatTest {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(9090);
        tomcat.setPort(9090);
        tomcat.setConnector(connector);
        tomcat.start();
        tomcat.getServer().await();
    }
}
