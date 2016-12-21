/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */
package com.gizwits.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by feel on 2016/11/7.
 */
public class CountDownLatchDemo implements Runnable {
    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    @Override
    public void run() {


        try {
            Thread.sleep(1000);

            System.out.println("等待其他人.....");
            countDownLatch.countDown();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {


        ExecutorService service = Executors.newFixedThreadPool(5);

        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();

        //  可以 把5 该成2 ，发现会一直等待中
        for (int i = 0; i < 5; i++) {
            service.submit(countDownLatchDemo);
        }

        // 等待
        countDownLatch.await();

        System.out.println("集合完成。。。。");


        service.shutdown();

        service.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);


        // end main
    }
}
