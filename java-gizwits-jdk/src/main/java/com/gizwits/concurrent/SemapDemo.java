package com.gizwits.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by feel on 2016/11/7.
 * <p>
 * 信号量
 */
public class SemapDemo implements Runnable {

    // 一次仅可以获取2个信号
    private Semaphore semaphore = new Semaphore(2);

    @Override
    public void run() {

        try {
            // 获取信号
            semaphore.acquire();
            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getId() + ":done!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            // 释放信号
            semaphore.release();
        }

    }


    public static void main(String[] args) {


        ExecutorService service = Executors.newFixedThreadPool(20);

        SemapDemo semapDemo = new SemapDemo();
        for (int i = 0; i < 20; i++) {

            service.submit(semapDemo);

        }
    }
}
