package com.mimu.simple.spring.file.lifecycle.d;

/**
 * author: mimu
 * date: 2019/1/20
 */
public class DomainA {

    private String a;
    private DomainB domainB;

    DomainA(DomainB b){
        this.domainB = b;
        this.a = "a";
    }

    @Override
    public String toString() {
        return "DomainA{" +
                "a='" + a + '\'' +
                ", domainB=" + domainB +
                '}';
    }
}
