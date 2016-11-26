package com.gizwits.bean;

/**
 * Created by feel on 16/4/3.
 */
public class User extends Person {

    public String hello() {
        // System.out.println("人要吃饭!!");


        return "--hello--";
    }


    @Override
    public String eat() {
        return "--开始吃饭--";
    }
}
