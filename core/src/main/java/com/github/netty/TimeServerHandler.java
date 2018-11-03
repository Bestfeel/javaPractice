package com.github.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

@Slf4j
public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {


        // 根据客户端的请求 做出判断
        String dateStr = DateTime.now().toString();

        //  因为 我们使用 StringDecoder  解码器

        //  数据 写入的时候 需要  ByteBuf  类型.  数据读取的时候 ,直接 强转 string 类型即可
        //  如果我们 有string  解码器 ,及不需要ByteBuf  类型. 直接 回写string 类型即可
        ByteBuf byteBuf = Unpooled.copiedBuffer((dateStr + System.getProperty("line.separator")).getBytes());

        //  有了string  编码器 , 直接回写  string 类型即可
        // ctx.writeAndFlush(byteBuf);
        ChannelFuture f = ctx.writeAndFlush(dateStr + System.getProperty("line.separator"));

        f.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {

                //assert f == future;

                log.info("发送完毕！，收到监听，关闭系");
                ctx.close();
            }
        });
        f.addListener(ChannelFutureListener.CLOSE);
        ReferenceCountUtil.release(dateStr);
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("....服务端  exceptionCaught ......");
        ctx.close();
    }
}


