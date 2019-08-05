package com.mimu.simple.sj.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * author: mimu
 * date: 2019/7/19
 */
public abstract class SimpleJettyServer {
    private static final String contextPath = "/";
    private static final String mappingUrl = "/*";
    private int port;
    private Class<?> appClazz;

    public SimpleJettyServer(int port, Class<?> clazz) {
        this.port = port;
        this.appClazz = clazz;
    }

    public void run() throws Exception {
        Server server = new Server(port);
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath(contextPath);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(getContext())), mappingUrl);
        contextHandler.addEventListener(new ContextLoaderListener(getContext()));
        server.setHandler(contextHandler);
        server.start();
        server.join();
    }

    public WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(appClazz);
        return context;
    }
}
