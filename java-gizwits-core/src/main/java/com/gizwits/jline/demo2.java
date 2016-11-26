package com.gizwits.jline;

import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;
import org.springframework.shell.core.annotation.CliCommand;

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
