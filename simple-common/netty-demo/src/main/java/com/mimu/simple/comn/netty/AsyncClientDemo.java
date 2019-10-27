package com.mimu.simple.comn.netty;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * author: mimu
 * date: 2018/11/7
 */
public class AsyncClientDemo {
    private String host;
    private int port;

    AsyncClientDemo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startClient() {
        new Thread(new AsyncClient(host, port), "AsyncClientDemo-1").start();
    }

    public static void main(String[] args) {
        new AsyncClientDemo("127.0.0.1", 8080).startClient();
    }

    class AsyncClient implements CompletionHandler<Void, AsyncClient>, Runnable {

        private AsynchronousSocketChannel asynchronousSocketChannel;
        private String host;
        private int port;
        private CountDownLatch latch;

        AsyncClient(String host, int port) {
            this.host = host;
            this.port = port;
            try {
                asynchronousSocketChannel = AsynchronousSocketChannel.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            latch = new CountDownLatch(1);
            asynchronousSocketChannel.connect(new InetSocketAddress(host, port), this, this);
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                asynchronousSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void completed(Void result, AsyncClient attachment) {
            byte[] request = "hello".getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(request.length);
            writeBuffer.put(request);
            writeBuffer.flip();
            asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if (attachment.hasRemaining()) {
                        asynchronousSocketChannel.write(attachment, attachment, this);
                    } else {
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        asynchronousSocketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                attachment.flip();
                                byte[] bytes = new byte[attachment.remaining()];
                                attachment.get(bytes);
                                try {
                                    String message = new String(bytes, "UTF-8");
                                    System.out.println("asyncClient receviced: " + message);
                                    latch.countDown();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                try {
                                    asynchronousSocketChannel.close();
                                    latch.countDown();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        asynchronousSocketChannel.close();
                        latch.countDown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void failed(Throwable exc, AsyncClient attachment) {
            try {
                asynchronousSocketChannel.close();
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
