package com.mimu.simple.comn.grpc;

import com.mimu.simple.comn.grpc.gen.GreeterGrpc;
import com.mimu.simple.comn.grpc.gen.HelloReply;
import com.mimu.simple.comn.grpc.gen.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class HelloWorldClient {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldClient.class);

    private ManagedChannel channel;
    private GreeterGrpc.GreeterBlockingStub blockingStub;

    HelloWorldClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    HelloWorldClient(ManagedChannel channel) {
        this.channel = channel;
        this.blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void greet(String message) {
        HelloRequest request = HelloRequest.newBuilder().setName(message).build();
        HelloReply response = blockingStub.sayHello(request);
        System.out.println("client send: " + request.getName());
        System.out.println("client receive: " + response.getMessage());
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

}
