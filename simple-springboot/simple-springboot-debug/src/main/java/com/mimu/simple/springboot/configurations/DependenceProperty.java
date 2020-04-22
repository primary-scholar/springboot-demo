package com.mimu.simple.springboot.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 author: mimu
 date: 2020/4/22
 */
@ConfigurationProperties(prefix = "a")
public class DependenceProperty {

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
        return "DependenceProperty{" +
                "anInt=" + anInt +
                ", anString='" + anString + '\'' +
                '}';
    }
}
