package com.mimu.simple.sd.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * author: mimu
 * date: 2019/8/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData implements Serializable {
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
