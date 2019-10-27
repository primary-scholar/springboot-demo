package com.mimu.simple.comn.netty.netty.c3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: mimu
 * date: 2018/11/10
 */
public class NettyProtobufServerDemo {

    private static final Logger logger = LoggerFactory.getLogger(NettyProtobufServerDemo.class);
    private int port;

    private NettyProtobufServerDemo(int port) {
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
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        ch.pipeline().addLast(new ProtobufDecoder(com.mimu.simple.comn.socket.netty.c3.SubModel.SubModelInfo.getDefaultInstance()));
                        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast(new ProtobufEncoder());
                        ch.pipeline().addLast(new ProtobufServerHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("NettyProtobufServerDemo start... port={}", port);
            channelFuture.channel().closeFuture().addListener(future -> {
                connectionGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            });
        } catch (InterruptedException e) {
            logger.error("NettyProtobufServerDemo start error", e);
        }
    }

    public static void main(String[] args) {
        new NettyProtobufServerDemo(8080).startServer();
    }

    class ProtobufServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelActive");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            logger.info("channelRead");
            com.mimu.simple.comn.socket.netty.c3.SubModel.SubModelInfo subModlInfo = (com.mimu.simple.comn.socket.netty.c3.SubModel.SubModelInfo) msg;
            System.out.println("server received: ");
            System.out.println(msg);
            ctx.writeAndFlush(getResponse(subModlInfo.getSubId()));
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelReadComplete");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.info("exceptionCaught error={}", cause.getStackTrace());
            ctx.close();
        }

        private com.mimu.simple.comn.socket.netty.c3.ResModel.ResModelInfo getResponse(int subId) {
            com.mimu.simple.comn.socket.netty.c3.ResModel.ResModelInfo.Builder builder = com.mimu.simple.comn.socket.netty.c3.ResModel.ResModelInfo.newBuilder();
            builder.setSubId(subId);
            builder.setRespCode(200);
            builder.setDesc("server received: " + builder.getSubId());
            return builder.build();
        }
    }

}
