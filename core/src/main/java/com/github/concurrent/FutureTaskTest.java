/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */
package com.github.concurrent;


import java.util.concurrent.*;

/**
 * Created by feel on 2016/11/29.
 */
public class FutureTaskTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {


        //  第一种方法：
        //构造FutureTask
        FutureTask<String> futureTask = new FutureTask<>(new CallData());


        ExecutorService service = Executors.newFixedThreadPool(1);


        service.submit(futureTask);


        //  第二种方法：

        CompletionService<String> completionService = new ExecutorCompletionService<>(service);
        completionService.submit(new CallData());

        System.out.println(completionService.take().get());


        service.shutdown();

        service.awaitTermination(100, TimeUnit.DAYS);
        //如果此时call()方法没有执行完成，则依然会等待
        System.out.println(futureTask.get());


    }
}

class CallData implements Callable<String> {

    @Override
    public String call() throws Exception {

        //模拟耗时的操作
        Thread.sleep(5000);
        return "success";
    }
}