package com.mimu.simple.comn.rocketmq;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.PushConsumer;

import java.io.IOException;
import java.util.Collections;

public class SimpleRocketMQConsumer {
    public static void main(String[] args) throws ClientException, InterruptedException, IOException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();
        //接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8081;xxx:8081。
        String endpoints = "localhost:8081";
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
                .setEndpoints(endpoints)
                .build();
        //订阅消息的过滤规则，表示订阅所有Tag的消息。
        String tag = "*";
        FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);
        //为消费者指定所属的消费者分组，Group需要提前创建。
        String consumerGroup = "ConsumerGroup";
        //指定需要订阅哪个目标Topic，Topic需要提前创建。
        String topic = "TopicTest";
        //初始化PushConsumer，需要绑定消费者分组ConsumerGroup、通信参数以及订阅关系。
        PushConsumer pushConsumer = provider.newPushConsumerBuilder()
                .setClientConfiguration(clientConfiguration)
                //设置消费者分组。
                .setConsumerGroup(consumerGroup)
                //设置预绑定的订阅关系。
                .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
                //设置消费监听器。
                .setMessageListener(messageView -> {
                    //处理消息并返回消费结果。
                    System.out.println("Consume message!!");
                    return ConsumeResult.SUCCESS;
                })
                .build();
        Thread.sleep(2000);
        //如果不需要再使用PushConsumer，可关闭该进程。
        pushConsumer.close();

    }
}