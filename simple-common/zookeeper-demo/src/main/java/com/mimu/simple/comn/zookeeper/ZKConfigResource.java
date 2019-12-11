package com.mimu.simple.comn.zookeeper;

import com.netflix.config.DynamicWatchedConfiguration;
import com.netflix.config.source.ZooKeeperConfigurationSource;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.netflix.config.ConfigurationManager.install;

/**
 author: mimu
 date: 2019/12/11
 */
public class ZKConfigResource {
    private static final AtomicBoolean initialization = new AtomicBoolean(false);
    private String zkAddress;
    private String rootPath;

    private CuratorFramework client;
    private ZooKeeperConfigurationSource zkConfigSource;

    public ZKConfigResource(String address, String path) {
        this.zkAddress = address;
        this.rootPath = path;
        if (initialization.compareAndSet(false, true)) {
            initAndStartConfiguration();
        }
    }

    private void initAndStartConfiguration() {
        client = CuratorFrameworkFactory.newClient(zkAddress, new ExponentialBackoffRetry(1000, 1));
        client.start();
        zkConfigSource = new ZooKeeperConfigurationSource(client, rootPath);
        try {
            zkConfigSource.start();
        } catch (Exception e) {
           // log.error("initAndStartConfiguration error", e);
        }
        DynamicWatchedConfiguration zkWatchedConfig = new DynamicWatchedConfiguration(zkConfigSource);
        install(zkWatchedConfig);
    }
}
