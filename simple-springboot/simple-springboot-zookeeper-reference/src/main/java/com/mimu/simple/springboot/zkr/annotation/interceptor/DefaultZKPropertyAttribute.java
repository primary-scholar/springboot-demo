package com.mimu.simple.springboot.zkr.annotation.interceptor;

/**
 * author: mimu
 * date: 2020/4/29
 */
public class DefaultZKPropertyAttribute implements ZKPropertyAttribute {
    private String zkReference;
    private String zkValue;

    public DefaultZKPropertyAttribute() {
    }

    public DefaultZKPropertyAttribute(String key, String value) {
        this.zkReference = key;
        this.zkValue = value;
    }

    public String getZkReference() {
        return zkReference;
    }

    public void setZkReference(String zkReference) {
        this.zkReference = zkReference;
    }

    public String getZkValue() {
        return zkValue;
    }

    public void setZkValue(String zkValue) {
        this.zkValue = zkValue;
    }

    @Override
    public String getReference() {
        return zkReference;
    }

    @Override
    public String getValue() {
        return zkValue;
    }
}
