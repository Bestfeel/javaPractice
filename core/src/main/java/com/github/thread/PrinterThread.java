/*
 * Copyright (c) 2017.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more.
 */

package com.github.thread;

/**
 * Created by feel on 2017/7/20.
 */
public class PrinterThread {
    public static void main(String[] args) {


        Thread thread = new Thread(WriteFile::wite);
        thread.start();

        //  end   main
    }
}

class WriteFile {

    static void wite() {
        System.out.println("WriteFile.wite");
    }

    static void open() {

        System.out.println("WriteFile.open");
    }
}
