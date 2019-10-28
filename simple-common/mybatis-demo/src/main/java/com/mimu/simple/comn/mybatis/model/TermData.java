package com.mimu.simple.comn.mybatis.model;

public class TermData {
    protected  int term_id;

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    @Override
    public String toString() {
        return "TermData{" +
                "term_id=" + term_id +
                '}';
    }
}
