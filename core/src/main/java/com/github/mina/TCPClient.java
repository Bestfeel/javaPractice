package com.github.mina;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
public class TCPClient {

    public static void main(String[] args) {
        //创建客户端发送消息
        IoConnector connector=new NioSocketConnector();
        //设置超时时间
        connector.setConnectTimeoutMillis(30000);
        //设置过滤器
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(
                        new TextLineCodecFactory(
                                Charset.forName("UTF-8"),
                                LineDelimiter.MAC.getValue(),
                                LineDelimiter.MAC.getValue()
                        )
                )
        );
       //注册IoHandler：
        connector.setHandler(new ClientHandler("你好！\r\n 大家好！"));
        //绑定端口
        connector.connect(new InetSocketAddress("localhost", 9123));

    }
}
class ClientHandler extends IoHandlerAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    private final String values;
    public ClientHandler(String values) {
        this.values = values;
    }
    @Override
    public void sessionOpened(IoSession session) {
        session.write(values);
    }
}