package com.mimu.simple.java.proxy;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class StaticProxy implements SubjectInterface {

    private SubjectInterface targect;

    StaticProxy(SubjectInterface targect) {
        this.targect = targect;
    }

    public String printInfo() {
        printBefore();
        String result = targect.printInfo();
        printAfter();
        return result;
    }

    @Override
    public String printInfoAgain() {
        printBefore();
        String result = targect.printInfoAgain();
        printAfter();
        return result;
    }

    private void printBefore() {
        System.out.println("Before");
    }

    private void printAfter() {
        System.out.println("After");
        System.out.println();
    }
}
