package com.mimu.simple.sj.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: mimu
 * date: 2019/7/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private long pid;
    private String nickName;
}
