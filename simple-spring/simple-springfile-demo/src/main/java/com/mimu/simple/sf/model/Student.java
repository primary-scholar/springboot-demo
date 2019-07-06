package com.mimu.simple.sf.model;

/**
 * author: mimu
 * date: 2018/11/23
 */
public class Student extends People{
    private int id;
    private String avator;
    private People people;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    protected String getInfo(){
        return "student";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", avator='" + avator + '\'' +
                ", people=" + people +
                '}';
    }
}
