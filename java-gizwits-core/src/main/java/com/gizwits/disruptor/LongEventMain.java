/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LongEventMain {
    private final static Logger LOGGER = LoggerFactory.getLogger(LongEventHandler.class);

    public static void onEvent1(LongEvent event, long sequence, boolean endOfBatch) {

        if (event.getValue() % 10000000 == 0) {
            event.set(event.getValue());
            LOGGER.info("Event1: " + event.toString());

        }

    }

    public static void onEvent2(LongEvent event, long sequence, boolean endOfBatch) {

        if (event.getValue() % 10000000 == 0) {
            event.set(event.getValue());
            LOGGER.info("Event2: " + event.toString());

        }

    }

    public static void onEvent3(LongEvent event, long sequence, boolean endOfBatch) {

        if (event.getValue() % 10000000 == 0) {

            event.set(event.getValue());
            LOGGER.info("Event3: " + event.toString());

        }

    }

    public static void main(String[] args) throws Exception {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // Specify the size of the ring buffer, must be power of 2.ringBuffer 缓存区大小
        int bufferSize = 1024 * 1024;

//     Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor);

        /**
         * 第一个参数用来在ring buffer中创建event，第二个参数是ring buffer的大小，
         * 第三个参数是消费者处理消息而使用的线程池。
         * 第四个参数是单或者多生产者模式，
         * 第五个参数是可选的等待策略。
         */
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                new LongEventFactory(), bufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
                // new YieldingWaitStrategy() 这是一种在效率和cpu资源的消耗之间进行折中的一种策略,小数据量可能会出现空循环等待,cpu 使用率较高
        );
        // Connect the handler 连接并且处理
        //   开启 3个线程处理一样的消息
        //注意这个方法可以使用职责链模式 ,可以连续处理handle 事件
        //  disruptor.handleEventsWith(new LongEventHandler());
        //disruptor.handleEventsWith(new LongEventHandler());
        disruptor.handleEventsWith(new LongEventHandler());

        /**
         * 也可是进行消息组合
         * //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3
         */
        // EventHandlerGroup<LongEvent> handlerGroup = disruptor.handleEventsWith(LongEventMain::onEvent1, LongEventMain::onEvent2);
        // handlerGroup.then(LongEventMain::onEvent3);


        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            //这里 我们可以避免 我们ringbuffer,容量爆满
            // if capacity less than 10%, don't use ringbuffer anymore
            if (ringBuffer.remainingCapacity() < bufferSize * 0.1) {
                LOGGER.warn("disruptor:ringbuffer avaliable capacity is less than 10 %");
                // do something

            } else {
                bb.putLong(0, l);
                producer.onData(bb);
            }


        }

        // disruptor.shutdown();

    }
}