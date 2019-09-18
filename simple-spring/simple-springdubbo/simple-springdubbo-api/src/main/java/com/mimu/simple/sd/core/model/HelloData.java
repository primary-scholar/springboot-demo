package com.mimu.simple.sd.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * author: mimu
 * date: 2019/9/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelloData implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
