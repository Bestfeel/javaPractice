/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits.test;

/**
 * Created by feel on 2016/12/22.
 */
public class App {


    public static void main(String[] args) {

        byte status = 1;

        status = (byte) (status + 2);
        System.out.println(status);
        status = (byte) (status + 4);

        System.out.println(status);

        status=1;
        status = (byte) (status | 1 << 1);
        System.out.println(status);



        status=2;
        System.out.println(status & (1 << 1));

        // end main

    }

}
