package com.mimu.simple.springboot.mybatis.model;

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
    private int id;
    private long pid;
    private String name;

    public String getName() {
        return StringUtils.isEmpty(name) ? "" : name;
    }
}
