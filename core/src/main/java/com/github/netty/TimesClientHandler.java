package com.github.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimesClientHandler extends ChannelHandlerAdapter {

    private final ByteBuf firstMessage;

    public TimesClientHandler() {

        byte[] req = ("request" + System.getProperty("line.separator")).getBytes();
        firstMessage = Unpooled.buffer(req.length);

        firstMessage.writeBytes(req);
        log.info("...客户端发起请求...");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("----handlerAdded---");
        //  ctx.writeAndFlush(firstMessage);
        //  有了string  编码器, 直接写string 类型即可
        ctx.writeAndFlush("request" + System.getProperty("line.separator"));

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        log.error("----exceptionCaught---");
        cause.printStackTrace();
        ctx.close();
    }
}
