package com.mimu.simple.sn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * author: mimu
 * date: 2018/11/7
 */
public class AsyncServerDemo {
    private int port;

    AsyncServerDemo(int port) {
        this.port = port;
    }

    public void startServer() {
        new Thread(new AsyncServer(port), "AsyncAsyncServerDemo-1").start();
    }

    public static void main(String[] args) {
        new AsyncServerDemo(8080).startServer();
    }

    class AsyncServer implements Runnable {

        private int port;
        CountDownLatch latch;
        AsynchronousServerSocketChannel asynchronousServerSocketChannel;

        AsyncServer(int port) {
            this.port = port;
            try {
                asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
                asynchronousServerSocketChannel.bind(new InetSocketAddress(this.port));
                System.out.println("server start...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            latch = new CountDownLatch(1);
            doAccept();
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void doAccept() {
            asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
        }

    }

    class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServer> {

        @Override
        public void completed(AsynchronousSocketChannel result, AsyncServer attachment) {
            attachment.asynchronousServerSocketChannel.accept(attachment, this);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            result.read(buffer, buffer, new ReadCompletionHandler(result));
        }

        @Override
        public void failed(Throwable exc, AsyncServer attachment) {
            exc.printStackTrace();
            attachment.latch.countDown();
        }
    }

    class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

        private AsynchronousSocketChannel asynchronousSocketChannel;

        ReadCompletionHandler(AsynchronousSocketChannel channel) {
            if (this.asynchronousSocketChannel == null) {
                this.asynchronousSocketChannel = channel;
            }
        }

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            attachment.flip();
            byte[] body = new byte[attachment.remaining()];
            attachment.get(body);
            String request = null;
            try {
                request = new String(body, "UTF-8");
                System.out.println("asyncServer received: " + request);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String response = "hello this is server...";
            System.out.println("asyncServer send: " + response);
            doWrite(response);
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                this.asynchronousSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void doWrite(String message) {
            byte[] bytes = message.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if (attachment.hasRemaining()) {
                        asynchronousSocketChannel.write(attachment, attachment, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        asynchronousSocketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
