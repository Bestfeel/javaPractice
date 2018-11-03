/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */
package com.github.annotation;

/**
 * Created by feel on 16/9/1.
 */
@Bean(value = "prcess")
public class Process {
    @Color
    public void process(String name, int age) {

        System.out.println("name-->" + name);
    }
}
