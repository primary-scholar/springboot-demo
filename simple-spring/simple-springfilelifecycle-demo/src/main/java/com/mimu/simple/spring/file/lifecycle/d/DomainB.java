package com.mimu.simple.spring.file.lifecycle.d;

/**
 * author: mimu
 * date: 2019/1/20
 */
public class DomainB {

    private String b;
    private DomainA domainA;

    DomainB(DomainA a){
        this.domainA = a;
        this.b = "b";
    }

    @Override
    public String toString() {
        return "DomainB{" +
                "b='" + b + '\'' +
                ", domainA=" + domainA +
                '}';
    }
}
