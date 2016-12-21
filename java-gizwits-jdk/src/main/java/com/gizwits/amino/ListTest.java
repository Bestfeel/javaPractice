/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits.amino;

import org.amino.ds.lockfree.LockFreeList;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by feel on 2016/11/27.
 * 无锁数据结构
 */
public class ListTest {


    public static void main(String[] args) throws InterruptedException {

        int numTask = 100;

        final List<Integer> lockFreeList = new LockFreeList<>();
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < numTask; i++) {

            service.submit(new Runnable() {
                private AtomicInteger integer = new AtomicInteger();

                @Override
                public void run() {

                    boolean add = lockFreeList.add(integer.getAndIncrement());

                    if (add) {
                        System.out.println("List Size= " + lockFreeList.size());
                    } else {
                        System.out.println("did not insert " + integer.get());
                    }
                }
            });
        }

        service.shutdown();

        service.awaitTermination(100, TimeUnit.DAYS);

        System.out.println("lockFreeList-->" + lockFreeList.size());


    }
}
