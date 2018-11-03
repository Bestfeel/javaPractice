package com.github.disruptor;

import lombok.Data;

/**
 * 定义事件
 */
@Data
public class LongEvent {
    private long value;
}