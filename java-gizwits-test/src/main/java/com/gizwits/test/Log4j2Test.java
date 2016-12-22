/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */
package com.gizwits.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by feel on 2016/12/22.
 */
public class Log4j2Test {

    final static  Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("This is an info log.");
        logger.warn("This is a warn log.");
        // end main
    }
}
