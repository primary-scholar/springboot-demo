package com.mimu.simple.mybatis.model;

/**
 * author: mimu
 * date: 2019/10/26
 */
public class UserData {
    private int id;
    private int person_id;
    private String pserson_name;

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

    public String getPserson_name() {
        return pserson_name;
    }

    public void setPserson_name(String pserson_name) {
        this.pserson_name = pserson_name;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", person_id=" + person_id +
                ", pserson_name='" + pserson_name + '\'' +
                '}';
    }
}
