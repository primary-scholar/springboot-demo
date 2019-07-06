package com.mimu.simple.sn;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * author: mimu
 * date: 2018/11/3
 */
public class NioClientDemo {
    private int port;
    private String host;

    NioClientDemo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startClient() {
        new Thread(new NioClient(host, port), "NioClientDemo-1").start();
    }

    public static void main(String[] args) {
        new NioClientDemo("127.0.0.1", 8080).startClient();
    }

    class NioClient implements Runnable {

        private int port;
        private String host;
        private Selector selector;
        private SocketChannel socketChannel;
        private volatile boolean stop;

        NioClient(String host, int port) {
            this.host = host;
            this.port = port;
            try {
                selector = Selector.open();  // Selector.open is equal with SelectorProvider.provider().openSelector();
                socketChannel = SocketChannel.open(); // SocketChannel.open is equal with SelectorProvider.provider().openSocketChannel();
                socketChannel.configureBlocking(false);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        @Override
        public void run() {
            try {
                doConnect();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey key;
                    while (iterator.hasNext()) {
                        key = iterator.next();
                        iterator.remove();
                        try {
                            handleKey(key);
                        } catch (IOException e) {
                            if (key != null) {
                                key.cancel();
                                if (key.channel() != null) {
                                    key.channel().close();
                                }
                            }
                            e.printStackTrace();
                        }
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

        public void handleKey(SelectionKey key) throws IOException {
            if (key.isValid()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    if (socketChannel.finishConnect()) {
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        doWrite(socketChannel);
                    } else {
                        System.exit(1);
                    }
                }
                if (key.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int readBytes = socketChannel.read(byteBuffer);
                    if (readBytes > 0) {
                        byteBuffer.flip();
                        byte[] bytes = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bytes);
                        System.out.println(new String(bytes, "UTF-8"));
                        this.stop = true;
                    } else if (readBytes < 0) {
                        key.cancel();
                        socketChannel.close();
                    }
                }
            }
        }

        private void doConnect() throws IOException {
            if (socketChannel.connect(new InetSocketAddress(host, port))) {
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }

        private void doWrite(SocketChannel socketChannel) throws IOException {
            byte[] message = "hello ".getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(message.length);
            byteBuffer.put(message);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            if (!byteBuffer.hasRemaining()) {
                System.out.println("send over and ok");
            }
        }

    }
}
