/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more.
 */

package com.github.apt;


import com.github.apt.annotation.BeanSet;


@BeanSet
class Service {
    public void show() {
        System.out.println("....show.....");
    }
}

@BeanSet
public class APTTest {
    public APTTest() {
    }

    public static void main(String[] args) {
        System.out.println("....");
    }
}
