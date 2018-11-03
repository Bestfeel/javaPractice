package com.github.concurrent;

public class TestCount implements Runnable {

    private static int num = 0;

    @Override
    public void run() {

        num++;

    }

    public static void main(String[] args) {


        int thread = 2;
        for (int j = 0; j < thread; j++) {

            new Thread(new TestCount()).start();
        }


        System.out.println(num);

    }
}
