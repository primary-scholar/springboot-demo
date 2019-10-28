package com.mimu.simple.spring.mybatis.model;

/**
 * author: mimu
 * date: 2019/10/26
 */
public class UserData {
    protected int id;
    protected int person_id;
    protected String person_name;

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

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", person_id=" + person_id +
                ", person_name='" + person_name + '\'' +
                '}';
    }
}
