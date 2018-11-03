package com.github.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;

public class ISerive {

    public static void main(String[] args) throws Exception {
        //1 编写IoService
        IoAcceptor acceptor = new NioSocketAcceptor();
        //  设置 缓冲区大小
        acceptor.getSessionConfig().setReadBufferSize(2048);
        //设置等待时间
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);


        //2  编写过滤器. 这里我们可以编写 很多类似的过滤器
        //  用于 调试 日志过滤器，用于记录所有的事件和请求日志.
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());

        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(
                        Charset.forName("UTF-8"),
                        LineDelimiter.MAC.getValue(),
                        LineDelimiter.MAC.getValue())
                )
        );

        //3  编写IoHandler 然后我们把这个IoHandler 注册到IoService：
        acceptor.setHandler(new MyIoHandler());


        // 4 绑定端口
        int port = 9123;
        acceptor.bind(new InetSocketAddress(port));
    }
}

/**
 * 这里只是简单的输出文本内容
 */
class MyIoHandler extends IoHandlerAdapter {
    private final static Logger log = LoggerFactory.getLogger(MyIoHandler.class);


    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {


        // 接收数据
        String str = message.toString();
        log.info("服务端接收数据 The message received is [" + str + "]");

        // 服务端给 客户端发送数据

        session.write(new Date());


        if (str.endsWith("quit")) {
            //  会话 关闭
            session.close(true);
            return;
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        //如果想使用短链接的方式
        session.close(true);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {

        log.info("session created ");

    }
}