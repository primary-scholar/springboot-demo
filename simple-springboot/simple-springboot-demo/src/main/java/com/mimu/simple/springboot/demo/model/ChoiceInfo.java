package com.mimu.simple.springboot.demo.model;

import lombok.Builder;
import lombok.Data;

/**
 author: mimu
 date: 2020/3/5
 */
@Builder
@Data
public class ChoiceInfo {
    private int id;
    private String ca;
    private String cb;
    private String cc;
    private String cd;
    private String ce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCe() {
        return ce;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }


}
