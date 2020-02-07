package com.mimu.simple.springboot.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;


/**
 * 在spring boot 中 添加 Filter,Servlet,Listener 共有两种方式
 * 1.使用spring 注解方式
 * 1.1 @ServletComponentScan, @WebFilter, @WebServlet, @webListener
 * 2.使用 RegistrationBean 进行 bean 的 注入 @Bean
 */

/**
 author: mimu
 date: 2020/2/5
 */

/**
 * 第一种方式 添加注解
 */
//@ServletComponentScan
@Configuration
public class CustomAdditionConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CustomFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("name", "userDataFilter");
        filterRegistrationBean.setName("userDataFilter");
        filterRegistrationBean.setOrder(10);
        return filterRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>();
        CustomServlet servlet = new CustomServlet();
        registrationBean.setServlet(servlet);
        registrationBean.setName(servlet.getClass().getSimpleName());
        registrationBean.setUrlMappings(Collections.singleton("/customServlet"));
        registrationBean.setLoadOnStartup(2);
        return registrationBean;
    }

    /**
     * 第一种方式 添加注解
     */
    //@WebServlet(name = "customServlet" ,urlPatterns = "/customServlet")
    static class CustomServlet extends HttpServlet {
        private static final Logger logger = LoggerFactory.getLogger(CustomServlet.class);

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            logger.info("customServlet deal url={}", req.getServletPath());
            PrintWriter writer = resp.getWriter();
            writer.write("customServlet deal request");
            writer.flush();
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }

        @Override
        public void init(ServletConfig config) throws ServletException {
            logger.info("customServlet init");
        }
    }


    /**
     * 第一种方式 添加注解
     */
    //@WebFilter(filterName = "customFilter", urlPatterns = "/*")
    static class CustomFilter implements Filter {
        private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            logger.info("requestInfo={}", request.getParameterMap().toString());
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {

        }
    }


}
