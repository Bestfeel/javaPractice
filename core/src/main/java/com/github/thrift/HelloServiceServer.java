package com.github.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

import java.net.InetSocketAddress;


public class HelloServiceServer {
    /**
     * 启动 Thrift 服务器
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // 设置服务端口为 7911 
            TServerTransport serverTransport = new TServerSocket(new InetSocketAddress("0.0.0.0", 7911));
            TThreadPoolServer.Args trArgs = new TThreadPoolServer.Args(serverTransport);
            // 设置协议工厂为 TBinaryProtocol.Factory
            Factory proFactory = new TBinaryProtocol.Factory(true, true);
            trArgs.protocolFactory(proFactory);
            // 关联处理器与 Hello 服务的实现
            TProcessor processor = new Hello.Processor(new HelloServiceImpl());
            trArgs.processor(processor);
            // 使用普通的socket来传输数据
            trArgs.transportFactory(new TTransportFactory());

            //  TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

//            TServer server = new TThreadPoolServer(processor, serverTransport,
//                    proFactory);

            // Use this for a multi threaded server
            TServer server = new TThreadPoolServer(trArgs);

            System.out.println("Start server on port 7911...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}