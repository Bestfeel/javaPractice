/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by feel on 2016/11/21.
 */
public class TestConncurrent {


    public static void main(String[] args) throws InterruptedException {


        final ExecutorService service = Executors.newFixedThreadPool(100);
        final int num = 10000;

        final Count count = new Count();

        for (int i = 0; i < num; i++) {

            final int a = i;
            service.execute(new Runnable() {

                long startTime = 0l;

                @Override
                public void run() {

                    startTime += 1;

                    if (startTime != 1) {
                        System.out.println("startTime ......." + startTime);
                    }

                    count.incr(1);
                    count.send(a);
                }
            });
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.DAYS);

        System.out.println("count...." + count.getI());
        System.out.println("result...." + count.send(0));


        // end  main
    }
}
