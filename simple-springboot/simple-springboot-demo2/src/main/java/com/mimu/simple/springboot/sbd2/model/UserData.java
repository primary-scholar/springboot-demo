package com.mimu.simple.springboot.sbd2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * author: mimu
 * date: 2019/6/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
