package com.mimu.simple.mybatis.model;

/**
 * author: mimu
 * date: 2019/10/26
 */
public class UserData {
    private int id;
    private int person_id;
    private String pName;

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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", person_id=" + person_id +
                ", pName='" + pName + '\'' +
                '}';
    }
}
