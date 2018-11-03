package com.github.bean;

public class User extends Person {

    public String hello() {
        return "--hello--";
    }

    @Override
    public String eat() {
        return "--开始吃饭--";
    }
}
