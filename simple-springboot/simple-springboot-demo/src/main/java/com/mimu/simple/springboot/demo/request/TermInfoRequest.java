package com.mimu.simple.springboot.demo.request;

/**
 * author: mimu
 * date: 2019/11/1
 */
public class TermInfoRequest {

    private int termId;
    private int personId;

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "TermInfo{" +
                "termId=" + termId +
                ", personId=" + personId +
                '}';
    }
}
