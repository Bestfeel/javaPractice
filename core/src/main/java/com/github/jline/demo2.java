/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */
package com.github.jline;

import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.JLineShellComponent;

/**
 * Created by feel on 16/8/5.
 */
public class demo2 {

    public static void main(String[] args) {


        Bootstrap bootstrap = new Bootstrap();

        JLineShellComponent shell = bootstrap.getJLineShellComponent();
        shell.start();
        shell.getSimpleParser().add(new HelloWorldCommands());

        //CommandResult cr = shell.executeCommand("");

      //  Object result = cr.getResult();




    }
}
