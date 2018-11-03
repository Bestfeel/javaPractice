/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.github.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by feel on 2016/11/7.
 * 可中断锁
 */
public class ReentrantLockDemo2 {


    public static void main(String[] args) {

        ReadWite readWite = new ReadWite();

        Thread writeThead = new Thread(new WriteThead(readWite));
        final Thread readThead = new Thread(new ReadThead(readWite));

        writeThead.start();
        readThead.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                for (; ; ) {
                    if (System.currentTimeMillis()
                            - start > 5000) {
                        System.out.println("不等了，尝试中断");
                        readThead.interrupt();  //此处中断读操作
                        break;
                    }
                }
            }
        }).start();
        // end main
    }
}

class ReadWite {

    private ReentrantLock lock = new ReentrantLock();




    /**
     * 写操作
     */
    public void write() {


        try {
            lock.lock();

            // 模拟耗时的操作

            Thread.sleep(10000);
            System.out.println("写入成功....");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 读操作
     */
    public void read() {

        try {
            // 注意这里，可以响应中断
            lock.lockInterruptibly();

            System.out.println("读取成功....");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}


class WriteThead implements Runnable {

    private ReadWite readWite;

    WriteThead(ReadWite readWite) {
        this.readWite = readWite;
    }

    @Override
    public void run() {
        readWite.write();
    }
}


class ReadThead implements Runnable {
    private ReadWite readWite;

    ReadThead(ReadWite readWite) {
        this.readWite = readWite;
    }

    @Override
    public void run() {

        readWite.read();
    }
}