package com.mimu.simple.springboot.ssb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * author: mimu
 * date: 2019/10/9
 */
@Configuration
public class UserFilterConfig {

    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new UserDataFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("name", "userDataFilter");
        filterRegistrationBean.setName("userDataFilter");
        filterRegistrationBean.setOrder(10);
        return filterRegistrationBean;
    }


    static class UserDataFilter implements Filter {
        private static final Logger logger = LoggerFactory.getLogger(UserDataFilter.class);

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
