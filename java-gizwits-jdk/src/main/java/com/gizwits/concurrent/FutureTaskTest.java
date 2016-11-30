package com.gizwits.concurrent;

import java.util.concurrent.*;

/**
 * Created by feel on 2016/11/29.
 */
public class FutureTaskTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //构造FutureTask
        FutureTask<String> futureTask = new FutureTask<>(new CallData());

        ExecutorService service = Executors.newFixedThreadPool(1);


        service.submit(futureTask);

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