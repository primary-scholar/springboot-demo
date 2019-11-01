package com.mimu.simple.springboot.demo.request;


/**
 * author: mimu
 * date: 2019/10/9
 */

public class UserInfoRequest {
    private int cid;
    private int pid;
    private String name;
    private UserType type;
    private TermInfoRequest termInfo;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(int type) {
        this.type = UserType.userType(type);
    }

    public TermInfoRequest getTermInfo() {
        return termInfo;
    }

    public void setTermInfo(TermInfoRequest termInfo) {
        this.termInfo = termInfo;
    }

    @Override
    public String toString() {
        return "UserInfoRequest{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", termInfo=" + termInfo +
                '}';
    }
}
