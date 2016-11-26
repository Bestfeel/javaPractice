package com.gizwits.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 发布事件(假设我们的一些数据数据来自于一些网络io.设备操作数据
 * <pre>
 *     Disruptor 的事件发布过程是一个两阶段提交的过程：
 * 　　第一步：先从 RingBuffer 获取下一个可以写入的事件的序号；
 * 　　第二步：获取对应的事件对象，将数据写入事件对象；
 * 　　第三部：将事件提交到 RingBuffer;
 * 事件只有在提交之后才会通知 EventProcessor 进行处理；
 * </pre>
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            event.set(bb.getLong(0));  // Fill with data
        } finally {
            //注意，最后的 ringBuffer.publish 方法必须包含在 finally 中以确保必须得到调用；
            // 如果某个请求的 sequence 未被提交，将会堵塞后续的发布操作或者其它的 producer。
            ringBuffer.publish(sequence);
        }
    }
}