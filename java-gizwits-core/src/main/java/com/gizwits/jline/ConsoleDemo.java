/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits.jline;

import java.io.IOException;

import java.util.List;
import java.util.zip.GZIPInputStream;

import jline.TerminalFactory;
import jline.console.ConsoleReader;
import jline.console.completer.FileNameCompleter;
import jline.console.completer.StringsCompleter;

import org.apache.commons.io.IOUtils;

public class ConsoleDemo {

	public static void main(String[] args) {
	 	System.setProperty("jline.shutdownhook", "true");
		try {
			ConsoleReader	console = new ConsoleReader();
			console.setPrompt("shell> ");

			List<String> readLines = IOUtils.readLines(new GZIPInputStream(ConsoleDemo.class.getClassLoader().getResourceAsStream("wordlist.txt.gz")));

			console.addCompleter(new StringsCompleter(readLines));
			console.addCompleter(new FileNameCompleter());


			String	line = null;
			console.println("请输入命令:");
			while ((line = console.readLine()) != null) {
				if(line.equalsIgnoreCase("q")){

					console.close();

					System.exit(1);

				}else{

					console.println(line);
					console.flush();
				}

			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
