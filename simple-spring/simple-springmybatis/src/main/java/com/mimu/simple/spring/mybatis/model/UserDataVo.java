package com.mimu.simple.spring.mybatis.model;

/**
 * author: mimu
 * date: 2019/10/27
 */
public class UserDataVo extends UserData {
    protected int term_id;

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    @Override
    public String toString() {
        return "UserDataVo{" +
                "term_id=" + term_id +
                ", id=" + id +
                ", person_id=" + person_id +
                ", person_name='" + person_name + '\'' +
                '}';
    }
}
