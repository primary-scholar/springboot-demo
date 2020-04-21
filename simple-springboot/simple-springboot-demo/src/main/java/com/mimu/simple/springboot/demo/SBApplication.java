package com.mimu.simple.springboot.demo;

import com.mimu.simple.springboot.demo.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 */

/**
 * servlet 容器接受请求 到处理完毕 并返回结果给 servlet 容器的处理流程
 * <p>
 * pre 在把请求交给DispatcherServlet 处理前 Servlet 容器会 构造 一个 FilterChain 并执行 filter 逻辑 (该过程给 用户提供执行 filter 的机会，用户编写的filter 在此执行
 * 1.servlet 容器接收到请求 交由 dispatcherServlet 来处理
 * 1.1 DispatcherServlet 接收到url请求，根据url 获取一个 HandlerExecutionChain (包含一个具体的 Controller 中的某个 method 和多个 拦截器) 来执行请求
 * 1.1.1 首先 根据 请求参数 实例化 controller 中该 method 的参数(如果方法参数中存在 非基本数据类型) 参数实例化过程使用 反射方式 BeanUtil.instances(constructor)
 * 实例化对象后 填充 field 属性
 * 1.1.2 调用 controller 的 method 方法 反射调用 method.invoke() 并返回
 * 1.1.3 根据 @RequestMapping 注解 对上一部的返回结果 进行 序列化操作 如 (json) 并把数据放到 response 中交由 servlet 容器
 * <p>
 * ps: 在获取 HandlerExecutionChain 的过程中 除了用户自定义的 使用 @Controller 标注的 controller(该 controller 属于 requestMappingHandler ) 外
 * spring mvc 中还包含多个 handler 如 ResourceHttpRequestHandler (该 handler 属于 SimpleUrlHandlerMapping  ）
 * 该 handler 用于处理静态资源的访问，当 request url 中没有匹配到 某个具体的controller 则使用 该 handler 处理 即 (404)
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
@Import(value = {ApplicationConfig.class})
public class SBApplication {
    public static void main(String[] args) {
        SpringApplication.run(SBApplication.class);
    }
}
