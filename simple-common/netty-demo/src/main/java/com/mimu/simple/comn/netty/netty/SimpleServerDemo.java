package com.mimu.simple.comn.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: mimu
 * date: 2018/11/13
 */
public class SimpleServerDemo {

    private static final Logger logger = LoggerFactory.getLogger(SimpleServerDemo.class);

    private int port;

    private SimpleServerDemo(int port) {
        this.port = port;
    }

    public void startServer() {
        EventLoopGroup connectionGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(connectionGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChildHandler());
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("SimpleServerDemo start... port={}", port);
            channelFuture.channel().closeFuture().addListener((ChannelFutureListener) future -> {
                connectionGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            });
        } catch (InterruptedException e) {
            logger.error("SimpleServerDemo start error", e);
        }

    }

    public static void main(String[] args) {
        new SimpleServerDemo(8080).startServer();
    }

    class ChildHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new Handler());
        }
    }

    /**
     * in netty there are two kinds of handlerAdapter inboundHandlerAdapter and outboundHandlerAdapter
     * the former one refer to a orient-point which the message streamed in
     * and the last refer to a orient-point which the message streamed out
     * <p>
     * ChannelInboundHandlerAdapter always using to decode the message form client
     */
    class Handler extends ChannelInboundHandlerAdapter {

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            logger.info("handlerAdded");
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelRegistered");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelActive");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            logger.info("channelRead");
            ctx.writeAndFlush(msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelReadComplete");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.error("exceptionCaught", cause);
        }
    }

}
