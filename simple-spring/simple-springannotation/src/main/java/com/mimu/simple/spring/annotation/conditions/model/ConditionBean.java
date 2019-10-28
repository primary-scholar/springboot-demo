package com.mimu.simple.spring.annotation.conditions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: mimu
 * date: 2019/7/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionBean {
    private String flag;
}
