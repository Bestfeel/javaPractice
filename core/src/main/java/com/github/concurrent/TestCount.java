/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */
package com.github.concurrent;

/**
 * Created by feel on 2016/11/22.
 */
public class TestCount implements Runnable {

    private static int num = 0;

    @Override
    public void run() {

        num++;

    }

    public static void main(String[] args) {


        int thread = 2;
        for (int j = 0; j < thread; j++) {

            new Thread(new TestCount()).start();
        }


        System.out.println(num);

    }
}
