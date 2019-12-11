package com.mimu.simple.comn.zookeeper;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.DynamicWatchedConfiguration;
import com.netflix.config.source.ZooKeeperConfigurationSource;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.retry.ExponentialBackoffRetry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.netflix.config.ConfigurationManager.*;


/**
 author: mimu
 date: 2019/12/11
 */
@Slf4j
public class ZKConfigCenter {

    static {
        ZKConfigCenter.create().zkAddress("localhost:2180").rootPath("/configuration").build();
    }

    public static Builder create() {
        return new Builder();
    }

    private static final class Builder {
        private String address;
        private String path;

        public Builder zkAddress(String zkAddress) {
            this.address = zkAddress;
            return this;
        }


        public Builder rootPath(String rootPath) {
            this.path = rootPath;
            return this;
        }

        public ZKConfigResource build() {
            return new ZKConfigResource(address, path);
        }
    }


    public static DynamicStringProperty getString(String key, String defaultValue) {
        return getString(key, defaultValue, null);
    }

    public static DynamicStringProperty getString(String key, String defaultValue, Runnable callable) {
        return DynamicPropertyFactory.getInstance().getStringProperty(key, defaultValue, callable);
    }

    /*public static class ZKConfigurationResource {
        private static final AtomicBoolean initialization = new AtomicBoolean(false);
        private String zkAddress;
        private String rootPath;

        private CuratorFramework client;
        private ZooKeeperConfigurationSource zkConfigSource;

        private ZKConfigurationResource(String address, String path) {
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
                log.error("initAndStartConfiguration error", e);
            }
            DynamicWatchedConfiguration zkWatchedConfig = new DynamicWatchedConfiguration(zkConfigSource);
            install(zkWatchedConfig);
        }
    }*/
}
