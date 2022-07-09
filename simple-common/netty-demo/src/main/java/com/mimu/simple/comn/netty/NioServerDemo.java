package com.mimu.simple.comn.netty;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * author: mimu
 * date: 2018/11/3
 */
public class NioServerDemo {
    private int port;

    NioServerDemo(int port) {
        this.port = port;
    }

    public void startServer() {
        new Thread(new NioServer(port), "NioServerDemo-1").start();
    }

    public static void main(String[] args) {
        new NioServerDemo(8080).startServer();
    }

    class NioServer implements Runnable {

        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        private volatile boolean stop;

        NioServer(int port) {
            try {
                selector = Selector.open();
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                final SelectionKey register = serverSocketChannel.register(selector, 0, null);
                register.interestOps(SelectionKey.OP_ACCEPT);
                serverSocketChannel.bind(new InetSocketAddress(port));
                System.out.println("server ...");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }

        public void stop() {
            this.stop = true;

        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        handleKey(key);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleKey(SelectionKey key) throws IOException {
            if (key.isValid()) {
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    final SelectionKey register = socketChannel.register(selector, 0, buffer);
                    register.interestOps(SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    final ByteBuffer buffer = (ByteBuffer) key.attachment();
                    // 每次实际读取的字节数
                    int readBytes = socketChannel.read(buffer);
                    if (readBytes > 0) {
                        buffer.flip();
                        final ByteBuffer writeBuffer = ByteBuffer.allocate(buffer.remaining());
                        writeBuffer.put(buffer);
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String message = new String(bytes, StandardCharsets.UTF_8);
                        System.out.println((message));
                        socketChannel.write(writeBuffer);
                        if (writeBuffer.hasRemaining()) {
                            key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                            key.attach(writeBuffer);
                        }
                    } else if (readBytes < 0) {
                        key.cancel();
                        socketChannel.close();
                    }
                }
                if (key.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    final ByteBuffer buffer = (ByteBuffer) key.attachment();
                    socketChannel.write(buffer);
                    if (!buffer.hasRemaining()) {
                        key.attach(null);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
            }
        }

    }

}