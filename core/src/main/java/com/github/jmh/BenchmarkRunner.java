package com.github.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


/**
 * 参考文档： http://blog.dyngr.com/blog/2016/10/29/introduction-of-jmh/
 *
 * @author guojie.yf
 * @Threads
 * @Fork
 * @OutputTimeUnit
 * @Param
 * @Benchmark
 * @Setup
 * @TearDown
 * @State
 */
public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {

        Options options = new OptionsBuilder()
                .include(BenchmarkRunner.class.getSimpleName())
//                .forks(1)
//                .warmupIterations(5)
//                .measurementIterations(2)
//                .output("./Benchmark.log")
                .build();
        new Runner(options).run();
    }

    

    /**
     * Measurement
     * 度量，其实就是一些基本的测试参数。
     * <p>
     * iterations 进行测试的轮次
     * time 每轮进行的时长
     * timeUnit 时长单位
     *
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MINUTES)
    @Warmup(iterations = 3)//预热轮数
    @Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
    public void init() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            TimeUnit.MICROSECONDS.sleep(10);
        }
    }

}
