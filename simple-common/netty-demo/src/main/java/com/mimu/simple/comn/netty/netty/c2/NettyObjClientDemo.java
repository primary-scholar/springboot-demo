package com.mimu.simple.comn.netty.netty.c2;

import com.mimu.simple.sn.netty.c2.model.ResModel;
import com.mimu.simple.sn.netty.c2.model.SubModel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: mimu
 * date: 2018/11/10
 */
public class NettyObjClientDemo {
    private static final Logger logger = LoggerFactory.getLogger(NettyObjClientDemo.class);
    private String host;
    private int port;

    private NettyObjClientDemo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void doConnecte() {
        int processors = Runtime.getRuntime().availableProcessors();
        EventLoopGroup workerGroup = new NioEventLoopGroup(processors);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                /*
                                  here ObjectDecoder can resolve tcp sticky packet and decoder the byte to object
                                  in osgi classloader domain because every bundle imports can reload without reboot jvm （hot deployment）
                                  so we didn't cache the classloader of one bundle
                                 */
                                ch.pipeline().addLast(new ObjectDecoder(1024,
                                        ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                                /*
                                  because our client send object to server so we should encode the object to byte
                                 */
                                ch.pipeline().addLast(new ObjectEncoder());
                                /*
                                  via the former two handler the ObjClientHandler can retrieve the every object from server
                                  so can convert the object msg to ResModel instance:
                                  refer to the ObjClientHandler channelRead method
                                 */
                                ch.pipeline().addLast(new ObjClientHandler());
                            }
                        });
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            logger.info("NettyObjClientDemo connected... port={}", port);
            channelFuture.channel().closeFuture().addListener(future -> workerGroup.shutdownGracefully());
        } catch (InterruptedException e) {
            logger.error("NettyObjClientDemo connected error", e);
        }
    }

    public static void main(String[] args) {
        new NettyObjClientDemo("127.0.0.1", 8080).doConnecte();
    }

    class ObjClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelActive");
            for (int i = 0; i < 10; i++) {
                ctx.writeAndFlush(getSub(i));
            }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            logger.info("channelRead");
            ResModel resModel = (ResModel) msg;
            System.out.println("client received:" + resModel);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            logger.info("channelReadComplete");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.info("channelActive");
            ctx.close();
        }

        private SubModel getSub(int subId) {
            SubModel subModel = new SubModel();
            subModel.setSubId(subId);
            subModel.setProductName("netty in practise");
            subModel.setAddress("around corner");
            return subModel;
        }
    }

}
