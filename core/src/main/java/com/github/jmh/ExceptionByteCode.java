package com.github.jmh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author guojie.yf
 */
public class ExceptionByteCode {

    public void test1() {
        long sum = 0;
        for (int i = 0; i < 100000; i++) {
            sum++;
        }
        System.out.println(sum);
        try {
            int result = 10 / 0;
            System.out.println(result);
            new FileInputStream(new File("test.json"));
        } catch (FileNotFoundException e) {
            //TODO
            System.out.println(e);
        } catch (Exception e) {
            //TODO
            System.out.println(e);
        } finally {
            //TODO
            System.out.println("finally");
        }
    }
}
