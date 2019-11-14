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
     *  在 ContextHandler.startContext() 中会执行 ContextLoaderListener 的 contextInitialized() 方法，该方法中会执行 initWebApplicationContext() 方法初始化 spring 中的 AnnotationConfigWebApplicationContext() 的refresh() 方法
     *  而在 initialize() 中会执行 DispatcherServlet 的 init() 方法，在该方法中会执行 initWebApplicationContext() 方法初始化 spring 中的 AnnotationConfigWebApplicationContext() 的refresh() 方法
     *  因此 此处若指定了 ContextLoaderListener() 则 spring 中的 refresh() 方法会执行两遍 ，故此处可以 不指定 ContextLoaderListener()
     * @throws Exception
     */

    /**
     * 在jetty 中 各组件都 继承 lifeCycle 所以 各组件都具有生命周期 都可以启动 停止等
     * servlet 容器 jetty 的启动过程如下:入口: server.start()-> server.doStart()
     * 在 server.doStart() 中 jetty 会依次启动1. QueuedThreadPool;2.ServerConnector;3.ServletContextHandler;4.ErrorHandler
     * 1.QueuedThreadPool 会初始化 ReservedThreadExecutor 并启动
     * 2.ServerConnector
     * 3.ServletContextHandler ServletContextHandler 继承自 ContextHandler 而在ServletContextHandler的doStart() 方法中会 执行ContextHandler.startContext()方法，
     * 在该方法中会执行 ServletHandler 的doStart() 方法 和 ServletHandler 的 initialize() 方法
     * ServletHandler.initialize() 中 最终会执行 DispatcherServlet 的 init() 方法 具体参考DispatcherServlet.init()(HttpServletBean 中)  DispatcherServlet->FrameworkServlet->HttpServletBean
     * 上述过程 最终会 调用到 initWebApplicationContext() 方法
     * 最终 会执行到 spring 中的 AnnotationConfigWebApplicationContext() 的refresh() 方法 至此 spring servlet 容器启动完毕
     *
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
