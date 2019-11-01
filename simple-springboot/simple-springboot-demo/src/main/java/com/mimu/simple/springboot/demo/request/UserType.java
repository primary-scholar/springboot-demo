package com.mimu.simple.springboot.demo.request;

/**
 * author: mimu
 * date: 2019/11/1
 */
public enum UserType {
    Error(-1), Publish(1), Media(1);

    private int type;

    UserType(int i) {
        this.type = i;
    }

    public int getType() {
        return type;
    }

    public static UserType userType(int type) {
        for (UserType userType : UserType.values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return Error;
    }
}
