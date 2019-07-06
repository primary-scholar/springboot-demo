package com.mimu.simple.sn.netty.c1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * author: mimu
 * date: 2018/11/8
 */
public class NettySimpleServerDemo {
    private static final Logger logger = LoggerFactory.getLogger(NettySimpleServerDemo.class);

    private int port;

    private NettySimpleServerDemo(int port) {
        this.port = port;
    }

    public void startServer() {
        int processors = Runtime.getRuntime().availableProcessors();
        EventLoopGroup connectionGroup = new NioEventLoopGroup(processors);
        EventLoopGroup workerGroup = new NioEventLoopGroup(processors);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(connectionGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        /**
                         * here we use LineBasedFrameDecoder resolve tcp sticky packet
                         * so we can received the every message from client properly
                         */
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        /**
                         * here we use StringDecoder to decode the message from client
                         * so we can retrieve the string message from client
                         */
                        ch.pipeline().addLast(new StringDecoder());
                        /**
                         * after former handler The ServerHandler can retrieve the every String message from client properly
                         * and we can convert the object msg to String instance:
                         * refer to the ServerHandler channelRead method
                         */
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("NettySimpleServerDemo start... port={}", port);
            channelFuture.channel().closeFuture().addListener(future -> {
                connectionGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            });
        } catch (InterruptedException e) {
            logger.error("NettySimpleServerDemo start error", e);
        }
    }

    public static void main(String[] args) {
        new NettySimpleServerDemo(8080).startServer();
    }

    /**
     * in netty there are two kinds of handlerAdapter inboundHandlerAdapter and outboundHandlerAdapter
     * the former one refer to a orient-point which the message streamed in
     * and the last refer to a orient-point which the message streamed out
     * <p>
     * ChannelInboundHandlerAdapter always using to decode the message form client
     */
    class ServerHandler extends ChannelInboundHandlerAdapter {
        private int count;

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelActive");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            logger.info("channelRead");
            String body = (String) msg;
            System.out.println("server received: " + body + " " + ++count);
            String response = "this is server and now is: " + System.currentTimeMillis() + System.getProperty("line.separator");

            ByteBuf buffer = Unpooled.buffer(response.getBytes().length);
            buffer.writeBytes(response.getBytes());
            ctx.writeAndFlush(buffer);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelReadComplete");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.error("exceptionCaught", cause);
            ctx.close();
        }
    }
}
