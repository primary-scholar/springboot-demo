package com.mimu.simple.java.proxy;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class Subject implements SubjectInterface {

    @Override
    public String printInfo() {
        String result = "printInfo method";
        System.out.println(result);
        return result;
    }

    @Override
    public String printInfoAgain() {
        String result = "printInfoAgain method";
        System.out.println(result);
        return result;
    }
}
