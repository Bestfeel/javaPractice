/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by feel on 2016/11/7.
 * <p>
 * 可重入锁
 */
public class ReentrantLockDemo implements Runnable {


    private ReentrantLock lock = new ReentrantLock();

    private static int i = 0;

    @Override
    public void run() {

        try {

            lock.lock();
            for (int j = 0; j < 100; j++) {
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new ReentrantLockDemo());
        Thread thread2 = new Thread(new ReentrantLockDemo());


        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();


        System.out.println(i);


        // end main
    }
}
