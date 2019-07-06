package com.mimu.simple.sk;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.util.Properties;

/**
 * author: mimu
 * date: 2019/5/14
 */
public class SimpleKafkaMultiKafkaClientConfigTest extends CustomKafkaClientConfig {

    @Test
    public void consume() {
        String topic = "kafka_topic_2";
        Properties properties = getConsumePro();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka-topic-1-group-1");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        SimpleKafkaMultiConsumer simpleKafkaMultiConsumer = SimpleKafkaMultiConsumer.getMultiConsumer(kafkaConsumer.partitionsFor(topic).size(), properties, topic, SimpleKafkaKafkaClientConfigTest::printInfo);
        simpleKafkaMultiConsumer.multiConsumerConsume();
    }

    @Test
    public void consume1() {
        String topic = "kafka-topic-1";
        Properties properties = getConsumePro();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka-topic-1-group-1");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,"kafka-topic-1-group-1-consumer-1");
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        SimpleKafkaMultiConsumer simpleKafkaMultiConsumer = SimpleKafkaMultiConsumer.getSingleConsumer(kafkaConsumer.partitionsFor(topic).size(), properties, topic, SimpleKafkaKafkaClientConfigTest::printInfo);
        simpleKafkaMultiConsumer.singleConsumerConsume();
    }

    @Test
    public void consume2() {
        String topic = "kafka-topic-1";
        Properties properties = getConsumePro();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka-topic-1-group-1");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,"kafka-topic-1-group-1-consumer-1");
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        SimpleKafkaMultiConsumer simpleKafkaMultiConsumer = SimpleKafkaMultiConsumer.getMultiSingleConsumer(kafkaConsumer.partitionsFor(topic).size(), properties, topic, SimpleKafkaKafkaClientConfigTest::printInfo);
        simpleKafkaMultiConsumer.multiSingleConsumerConsume();
    }

    public static void main(String[] args) {
        new SimpleKafkaMultiKafkaClientConfigTest().consume();
    }
}