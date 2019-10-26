package com.mimu.simple.mybatis;

/**
 * author: mimu
 * date: 2019/10/26
 */
public class UserData {
    private int id;
    private int pId;
    private String pName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
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
                ", pId=" + pId +
                ", pName='" + pName + '\'' +
                '}';
    }
}
