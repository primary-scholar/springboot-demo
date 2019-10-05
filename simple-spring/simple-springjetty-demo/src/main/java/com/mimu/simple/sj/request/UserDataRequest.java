package com.mimu.simple.sj.request;

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
public class UserDataRequest {
    private long pid;
    private int termId;
}
