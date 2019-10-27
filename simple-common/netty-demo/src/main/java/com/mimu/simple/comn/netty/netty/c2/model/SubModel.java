package com.mimu.simple.comn.netty.netty.c2.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * author: mimu
 * date: 2018/11/10
 */
public class SubModel implements Serializable {
    private int subId;
    private String productName;
    private String address;

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
