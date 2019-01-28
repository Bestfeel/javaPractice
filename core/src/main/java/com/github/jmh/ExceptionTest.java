package com.github.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author guojie.yf
 */
@BenchmarkMode({Mode.AverageTime, Mode.SampleTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ExceptionTest {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(ExceptionTest.class.getSimpleName())
                .warmupIterations(1)
                .measurementIterations(2)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void test1() {
        long sum = 0;
        for (int i = 0; i < 100000; i++) {
            sum++;
            int result = 10 / 1;
            result++;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e) {
            //TODO
            sum++;
            e.printStackTrace();
        }

    }

    @Benchmark
    public void test2() {
        long sum = 0;
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            for (int i = 0; i < 100000; i++) {
                sum++;
                int result = 10 / 1;
                result++;
            }
        } catch (Exception e) {
            //TODO
            sum++;
            e.printStackTrace();
        }
    }

    @Benchmark
    public void test3() {
        long sum = 0;
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            for (int i = 0; i < 100000; i++) {
                sum++;
                int result = 10 / 0;
                result++;
            }
        } catch (Exception e) {
            //TODO
            sum++;
            e.printStackTrace();
        }
    }

}
