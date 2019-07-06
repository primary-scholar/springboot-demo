package com.mimu.simple.sn.netty.c2.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * author: mimu
 * date: 2018/11/10
 */
public class ResModel implements Serializable {
    private int subId;
    private int respCode;
    private String desc;

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
