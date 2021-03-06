package com.github.concurrent;

public class Count {


    private int i = 0;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void incr(int i) {

        this.i += i;
    }


    public int send(int i) {

        int a = 0;

        try {
            Thread.sleep(randomIntValue(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a += i;

        return a;
    }

    public static int randomIntValue(int num) {

        return new Double(Math.floor(Math.random() * num)).intValue();

    }

}
