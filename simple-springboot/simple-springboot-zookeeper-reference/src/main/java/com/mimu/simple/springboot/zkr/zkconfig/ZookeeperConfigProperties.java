package com.mimu.simple.springboot.zkr.zkconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "spring.zookeeper.center"
)
public class ZookeeperConfigProperties {
    private String address;
    private String path;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
