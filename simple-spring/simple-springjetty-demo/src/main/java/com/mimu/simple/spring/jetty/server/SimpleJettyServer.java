package com.mimu.simple.spring.jetty.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
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

    /**
     *  servlet 容器在启动过程中 会首先初始化 servletContext 的上下文 即 startContext() 然后初始化 servlet 容器 即 initialize()
     *  在 startContext() 中会执行 ContextLoaderListener 的 contextInitialized() 方法，该方法中会执行 initWebApplicationContext() 方法初始化 spring 中的 AnnotationConfigWebApplicationContext() 的refresh() 方法
     *  而在 initialize() 中会执行 DispatcherServlet 的 init() 方法，在该方法中会执行 initWebApplicationContext() 方法初始化 spring 中的 AnnotationConfigWebApplicationContext() 的refresh() 方法
     *  因此 此处若指定了 ContextLoaderListener() 则 spring 中的 refresh() 方法会执行两遍 ，故此处可以 不指定 ContextLoaderListener()
     * @throws Exception
     */
    public void run() throws Exception {
        Server server = new Server(port);
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath(contextPath);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(getContext())), mappingUrl);
        //contextHandler.addEventListener(new ContextLoaderListener(getContext()));
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
