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
 * author: mimu
 * date: 2019/12/23
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
            String local = System.getProperty("server_ip");
            if (StringUtils.isNotEmpty(local)){
                String localPrefix = local.substring(0, getPointIndexWithSpecificOrdial(local, 2));
                for (Invoker invoker : invokers) {
                    String host = invoker.getUrl().getHost();
                    String hostPrefix = host.substring(0, getPointIndexWithSpecificOrdial(host, 2));
                    if (localPrefix.equals(hostPrefix)) {
                        filters.add(invoker);
                    }
                }
            }
        }
        return filters.size() > 0 ? filters : invokers;
    }

    private int getPointIndexWithSpecificOrdial(String origin, int ordinal) {
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
