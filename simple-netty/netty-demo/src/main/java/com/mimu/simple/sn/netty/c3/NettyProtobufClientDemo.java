package com.mimu.simple.sn.netty.c3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: mimu
 * date: 2018/11/11
 */
public class NettyProtobufClientDemo {
    private static final Logger logger = LoggerFactory.getLogger(NettyProtobufClientDemo.class);
    private String host;
    private int port;

    private NettyProtobufClientDemo(String host, int port) {
        this.host = host;
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
                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        ch.pipeline().addLast(new ProtobufDecoder(com.mimu.simple.socket.netty.c3.SubModel.SubModelInfo.getDefaultInstance()));
                        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast(new ProtobufEncoder());
                        ch.pipeline().addLast(new ProtobufClientHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            logger.info("NettyProtobufClientDemo connected... port={}", port);
            channelFuture.channel().closeFuture().addListener(future -> workerGroup.shutdownGracefully());
        } catch (InterruptedException e) {
            logger.error("NettyProtobufClientDemo connected error", e);
        }
    }

    public static void main(String[] args) {
        new NettyProtobufClientDemo("127.0.0.1", 8080).doConnect();
    }

    class ProtobufClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelActive");
            for (int i = 0; i < 10; i++) {
                ctx.writeAndFlush(creatSumModelInfo(i));
            }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            logger.info("channelRead");
            System.out.println("client received:");
            System.out.println(msg);
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

        private com.mimu.simple.socket.netty.c3.SubModel.SubModelInfo creatSumModelInfo(int subId) {
            com.mimu.simple.socket.netty.c3.SubModel.SubModelInfo.Builder builder = com.mimu.simple.socket.netty.c3.SubModel.SubModelInfo.newBuilder();
            builder.setSubId(subId);
            builder.setProductName("netty in practise");
            builder.setAddress("around corner");
            return builder.build();
        }
    }
}
