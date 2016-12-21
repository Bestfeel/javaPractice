/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits;

import org.junit.Test;

/**
 * Created by feel on 2016/11/14.
 */
public class Test1 {

    /**
     * 获取操作系统的架构
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {


        System.out.println(System.getProperties());

        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("sun.arch.data.model"));
    }

}
