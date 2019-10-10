package com.mimu.simple.springboot.ssbd1.model;

import org.springframework.util.StringUtils;

/**
 * author: mimu
 * date: 2019/6/29
 */
public class UserData {
    private long pid;
    private String nickName;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getNickName() {
        return StringUtils.isEmpty(nickName) ? "" : nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
