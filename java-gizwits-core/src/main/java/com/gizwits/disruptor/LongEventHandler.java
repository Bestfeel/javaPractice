package com.gizwits.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义事件处理的具体实现
 */
public class LongEventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    private final static Logger LOGGER = LoggerFactory.getLogger(LongEventHandler.class);

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
            LOGGER.info("Event: " + event.toString());

        }

    }
}