/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

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
