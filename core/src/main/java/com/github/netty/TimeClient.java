package com.github.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by feel on 16/4/12.
 */
@Slf4j
public class TimeClient {

    public void connect(int port, String host) throws Exception {

        // 配置客户端的nio 线程组

        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)  //  指定nio 的模式
                    .option(ChannelOption.TCP_NODELAY, true)//设置tcp  参数
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ch.pipeline().addLast(
                                    new LineBasedFrameDecoder(1024));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new LoggingHandler());
                            ch.pipeline().addLast(new TimesClientHandler());
                        }
                    });
            //发起异步连接操作

            ChannelFuture connect = bootstrap.connect(host, port).sync();

            //  等待客户端链路关闭
            connect.channel().closeFuture().sync();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //  优雅的退出
            group.shutdownGracefully();
            log.info(".... 优雅的退出....");
        }

    }


    public static void main(String[] args) throws Exception {
        new TimeClient().connect(9000, "127.0.0.1");
        //  end   main
    }
}
