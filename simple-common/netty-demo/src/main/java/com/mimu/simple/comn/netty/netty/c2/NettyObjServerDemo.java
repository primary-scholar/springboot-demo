package com.mimu.simple.comn.netty.netty.c2;

import com.mimu.simple.sn.netty.c2.model.ResModel;
import com.mimu.simple.sn.netty.c2.model.SubModel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: mimu
 * date: 2018/11/10
 */
public class NettyObjServerDemo {
    private static final Logger logger = LoggerFactory.getLogger(NettyObjServerDemo.class);
    private int port;

    private NettyObjServerDemo(int port) {
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
                        /*
                          here ObjectDecoder can resolve tcp sticky packet and decoder the byte to object
                         */
                        ch.pipeline().addLast(new ObjectDecoder(1024 * 1024,
                                ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        /*
                          because our server send object to client so we should encode the object to byte
                         */
                        ch.pipeline().addLast(new ObjectEncoder());
                        /*
                          via the former two handler the ObjServerHandler can retrieve the every object from client
                          so can convert the object msg to SubModel instance:
                          refer to the ObjServerHandler channelRead method
                         */
                        ch.pipeline().addLast(new ObjServerHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("NettyObjServerDemo start... port={}", port);
            channelFuture.channel().closeFuture().addListener(future -> {
                connectionGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            });
        } catch (InterruptedException e) {
            logger.error("NettyObjServerDemo start error", e);
        }
    }

    public static void main(String[] args) {
        new NettyObjServerDemo(8080).startServer();
    }

    class ObjServerHandler extends ChannelInboundHandlerAdapter {

        /**
         * this method will be invoked when the client and server build the connection
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelActive");
        }

        /**
         * this method will be invoked when the server received the message from client
         *
         * @param ctx
         * @param msg
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            logger.info("channelRead");
            SubModel subModel = (SubModel) msg;
            ctx.writeAndFlush(getResponse(subModel.getSubId()));
            System.out.println("server received: " + msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelReadComplete");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.info("exceptionCaught error={}", cause.getStackTrace().toString());
            ctx.close();
        }

        private ResModel getResponse(int subId) {
            ResModel resModel = new ResModel();
            resModel.setSubId(subId);
            resModel.setRespCode(200);
            resModel.setDesc("server received the order: " + subId);
            return resModel;
        }
    }

}
