package com.mimu.simple.comn.mybatis.model;

public class UserDataResultMap {
    protected int id;
    protected int person_id;
    protected String person_name;

    protected TermData termData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public TermData getTermData() {
        return termData;
    }

    public void setTermData(TermData termData) {
        this.termData = termData;
    }

    @Override
    public String toString() {
        return "UserDataResultMap{" +
                "id=" + id +
                ", person_id=" + person_id +
                ", person_name='" + person_name + '\'' +
                ", termData=" + termData +
                '}';
    }
}
