package com.mimu.simple.spring.dubbo.consumer.router.factory;

import com.mimu.simple.spring.dubbo.consumer.router.CustomDubboDataCenterRouter;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.cluster.Router;
import org.apache.dubbo.rpc.cluster.RouterFactory;

/**
 author: mimu
 date: 2019/12/23
 */
@Activate(order = 500)
public class CustomDubboDataCenterRouterFactory implements RouterFactory {

    public static final String NAME = "dbCenterRouter";

    @Override
    public Router getRouter(URL url) {
        return new CustomDubboDataCenterRouter(url);
    }
}
