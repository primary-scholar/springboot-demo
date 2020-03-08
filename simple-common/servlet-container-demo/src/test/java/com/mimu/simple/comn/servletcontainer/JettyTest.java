package com.mimu.simple.comn.servletcontainer;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 author: mimu
 date: 2020/1/14
 */
public class JettyTest {
    public static void main(String[] args) throws Exception {
        int port = 9091;
        Server server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");
        DispatcherServlet dispatcherServlet = new DispatcherServlet(new AnnotationConfigWebApplicationContext());
        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
        contextHandler.addServlet(servletHolder, "/*");

        HandlerCollection collection = new HandlerCollection();
        collection.setHandlers(new Handler[]{contextHandler, new DefaultHandler()});
        server.setHandler(collection);

        server.start();
        server.join();
    }

}
