package com.github.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by feel on 16/9/27.
 */
public class HttpServerFile {

    private static final Logger logger = LoggerFactory.getLogger(HttpServerFile.class);

    public static void bind(int port) {

        // 配置 服务端的nio 线程组,  第一个线程组接收client端连接的
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        //实际的逻辑操作
        EventLoopGroup childGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(parentGroup, childGroup)
                    // 指定nio 的模式
                    .channel(NioServerSocketChannel.class)
                    //是否启用心跳机制，保持连接。
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //是否一有数据就马上发送。
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //设置 tcp 的参数.服务端处理线程全忙后，允许多少个新请求进入等待。
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 加入http的解码器
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            // 加入HttpObjectAggregator 解码器，作用是他会把多个消息转换为单一的FullHttpRequest 或者 FullHttpResponse
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            //加入http的编码器
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            // 加入chunked 主要作用是支持异步发送的码流（大文件传输），但不占用过多的内存，防止java内存溢出
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            //  加入自定义处理文件的服务器的业务逻辑
                            //Remove the following line if you don't want automatic content compression.
                            //p.addLast("deflater", new HttpContentCompressor());
                        }
                    });


            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            channelFuture.channel().closeFuture().sync();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
            logger.info("...服务端资源释放..");

        }
    }

    public static void main(String[] args) {
        bind(8080);

    }
}
