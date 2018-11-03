/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.github.rx2java;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by feel on 2016/12/5.
 */
public class HelloRx2java {

    private final static Logger logger = LoggerFactory.getLogger(HelloRx2java.class);

    public static void main(String[] args) {


        Flowable.create(e -> {
            for (int i = 0; i < 10000; i++) {
                e.onNext(i);
            }
            e.onComplete();
            //指定背压处理策略，抛出异常
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .subscribe(e -> {
                    logger.info("JG:{}", e.toString());
                    Thread.sleep(1000);
                }, throwable -> {
                    logger.info("JG:{}", throwable.toString());
                });


    }
}
