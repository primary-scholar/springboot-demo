package com.mimu.simple.comn.netty.netty.c1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: mimu
 * date: 2018/11/8
 */
public class NettySimpleClientDemo {
    private static final Logger logger = LoggerFactory.getLogger(NettySimpleClientDemo.class);

    private String peer;
    private int port;

    private NettySimpleClientDemo(String host, int port) {
        this.peer = host;
        this.port = port;
    }

    public void doConnect() {
        int processors = Runtime.getRuntime().availableProcessors();
        EventLoopGroup workerGroup = new NioEventLoopGroup(processors);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        /*
                          here we use LineBasedFrameDecoder resolve tcp sticky packet
                          so we can received the every message from client properly
                         */
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        /*
                          here we use StringDecoder to decode the message from client
                          so we can retrieve the string message from client
                         */
                        ch.pipeline().addLast(new StringDecoder());
                        /*
                          after former handler The ServerHandler can retrieve the every String message from client properly
                          and we can convert the object msg to String instance:
                          refer to the ServerHandler channelRead method
                         */
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(peer, port).sync();
            logger.info("NettySimpleClientDemo connected... port={}", port);
            channelFuture.channel().closeFuture().addListener(future -> workerGroup.shutdownGracefully());
        } catch (InterruptedException e) {
            logger.error("NettySimpleClientDemo connected error", e);
        }
    }

    public static void main(String[] args) {
        new NettySimpleClientDemo("127.0.0.1", 8080).doConnect();
    }

    class ClientHandler extends ChannelInboundHandlerAdapter {
        private int counter;
        private byte[] request;

        ClientHandler() {
            request = ("this is client" + System.getProperty("line.separator")).getBytes();
        }

        /**
         * this method will be invoked when the client and server build the connection
         * here when the connection was build we send ten times request message to server
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelActive");
            ByteBuf message;
            for (int i = 0; i < 10; i++) {
                message = Unpooled.buffer(request.length);
                message.writeBytes(request);
                ctx.writeAndFlush(message);
            }
        }

        /**
         * this method will be invoked when the server send message to client
         *
         * @param ctx
         * @param msg
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            logger.info("channelRead");
            String body = (String) msg;
            System.out.println("client received: " + body + "; and counter is: " + ++counter);
            ByteBuf buf = Unpooled.copiedBuffer(body.getBytes());
            ctx.writeAndFlush(buf);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelReadComplete");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.info("exceptionCaught");
            ctx.close();
        }
    }

}
