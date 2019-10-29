package com.mimu.simple.comn.mybatis.model;

public class TermData {
    protected int id;
    protected  int term_id;
    protected int person_id;

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    @Override
    public String toString() {
        return "TermData{" +
                "id=" + id +
                ", term_id=" + term_id +
                ", person_id=" + person_id +
                '}';
    }
}
