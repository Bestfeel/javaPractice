package com.gizwits.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 定义事件工厂
 */
public class LongEventFactory implements EventFactory<LongEvent>
{
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}