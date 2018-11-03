package com.github.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Created by feel on 16/5/8.
 * 消息处理 handle
 */
@Slf4j
public class ServerSimpleHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        log.info("服务端收到消息.." + msg);

        ChannelFuture future = ctx.writeAndFlush("ServerSimpleHandler 处理.." + new Date().toString() + "\n");
        //  发送消息完毕后 ，关闭和client 的连接
        future.addListener(ChannelFutureListener.CLOSE);
    }
}
