package com.github.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 定义事件处理的具体实现
 */
@Slf4j
public class LongEventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        try {
            onEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {

        if (event.getValue() % 10000000 == 0) {
            log.info("Event: " + event.toString());
        }
    }
}