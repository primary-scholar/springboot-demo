package com.mimu.simple.spring.dubbo.consumer.router;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.router.AbstractRouter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.dubbo.rpc.cluster.Constants.FORCE_KEY;
import static org.apache.dubbo.rpc.cluster.Constants.PRIORITY_KEY;

/**
 author: mimu
 date: 2019/12/23
 */
public class CustomDubboDataCenterRouter extends AbstractRouter {

    public CustomDubboDataCenterRouter(URL url) {
        this.url = url;
        this.priority = url.getParameter(PRIORITY_KEY, 200);
        this.force = url.getParameter(FORCE_KEY, false);
    }

    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        List<Invoker<T>> filters = new ArrayList<>();
        if (!CollectionUtils.isEmpty(invokers)) {
            for (Invoker invoker : invokers) {
                String host = invoker.getUrl().getHost();
                String local = System.getProperty("server_ip");
                if (matchDbCenterByIp(host, local)) {
                    filters.add(invoker);
                }
            }
        }
        return filters.size() > 0 ? filters : invokers;
    }

    private boolean matchDbCenterByIp(String remote, String local) {
        String remotePrefix = remote.substring(0, getPointIndex(remote, 2));
        String localPrefix = local.substring(0, getPointIndex(local, 2));
        return remotePrefix.equals(localPrefix);
    }

    private int getPointIndex(String origin, int ordinal) {
        if (StringUtils.isEmpty(origin) || ordinal <= 0) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < origin.length(); i++) {
            char c = origin.charAt(i);
            if (c == '.') {
                index++;
            }
            if (index >= ordinal) {
                return i;
            }
        }
        return 0;
    }
}
