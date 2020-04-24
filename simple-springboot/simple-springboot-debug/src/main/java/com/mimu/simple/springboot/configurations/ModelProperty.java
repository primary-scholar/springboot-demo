package com.mimu.simple.springboot.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 author: mimu
 date: 2020/4/22
 */


/**
 * 这里 @ConfigurationProperties 标志 该 类中的 字段属性 可以来自 外部某个配置文件
 *
 * 若 要生成 该 类的 实例 建议做法使用 @EnableConfigurationProperties(ModelProperty.class)
 * 生成该 实例
 */
@ConfigurationProperties(prefix = "a")
public class ModelProperty {

    private int anInt;
    private String anString;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public String getAnString() {
        return anString;
    }

    public void setAnString(String anString) {
        this.anString = anString;
    }

    @Override
    public String toString() {
        return "ModelProperty{" +
                "anInt=" + anInt +
                ", anString='" + anString + '\'' +
                '}';
    }
}
