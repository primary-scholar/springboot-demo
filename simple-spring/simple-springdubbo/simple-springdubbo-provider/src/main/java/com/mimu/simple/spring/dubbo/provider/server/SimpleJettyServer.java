/*
package com.mimu.simple.spring.dubbo.provider.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

*/
/**
 * author: mimu
 * date: 2019/7/19
 *//*

public abstract class SimpleJettyServer {
    protected static AnnotationConfigWebApplicationContext context;
    protected static final String contextPath = "/";
    protected static final String mappingUrl = "/*";
    protected int port;
    protected Class<?> appClazz;

    public SimpleJettyServer(int port, Class<?> clazz) {
        this.port = port;
        this.appClazz = clazz;
    }

    public void run() throws Exception {
        initContext();
        Server server = new Server(port);
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath(contextPath);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(getContext())), mappingUrl);
        contextHandler.addEventListener(new ContextLoaderListener(getContext()));
        server.setHandler(contextHandler);
        server.start();
        server.join();
    }

    public void initContext() {
        context = new AnnotationConfigWebApplicationContext();
        context.register(appClazz);
    }

    public WebApplicationContext getContext() {
        return context;
    }

}
*/
