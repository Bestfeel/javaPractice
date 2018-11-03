package com.github.thread;

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
