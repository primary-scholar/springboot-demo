package com.mimu.simple.spring.hystrix.dao;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * author: mimu
 * date: 2019/8/30
 */
@Repository
public class CommonRepository {
    private static final Logger logger = LoggerFactory.getLogger(CommonRepository.class);

    /**
     * 在HystrixCommand实现的run()方法中跑出异常时，除了HystrixBadRequestException之外，
     * 其他异常均会被Hystrix认为命令执行失败并处罚服务降级的处理逻辑,下面的方法抛出的异常会导致主线程
     * 异常退出
     * <p>
     * hystrix command execute 默认使用线程池方式执行，根据 maxQueueSize 来生成 blockingQueue，
     * maxQueueSize>0，为linkBlockingQueue，<0 为SynchrousQueue，默认为后者，
     * fallback execute 默认使用信号量的方式执行，可以使用
     * HystrixPropertiesManager.FALLBACK_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS
     * 来指定 fallback 的并发量
     *
     * @param s
     * @return
     */
    @HystrixCommand(fallbackMethod = "getRemoteInfoFallBack",
            commandProperties = {
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "50"),
                    @HystrixProperty(name = HystrixPropertiesManager.FALLBACK_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "1")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = HystrixPropertiesManager.CORE_SIZE, value = "10"),
                    @HystrixProperty(name = HystrixPropertiesManager.MAX_QUEUE_SIZE, value = "-1")
            })
    public String getRemoteInfo(String s) {
        int random = (int) (Math.random() * 100);
        if (random > 50) {
            throw new HystrixBadRequestException(String.valueOf(random));
        }
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = "normal logic";
        logger.info("s={},value={}", s, value);
        return value;
    }

    public String getRemoteInfoFallBack(String s, Throwable throwable) {
        logger.error("s={},getRemoteInfo fallBack,exception=", s, throwable);
        return "getRemoteInfo fallBack";
    }
}
