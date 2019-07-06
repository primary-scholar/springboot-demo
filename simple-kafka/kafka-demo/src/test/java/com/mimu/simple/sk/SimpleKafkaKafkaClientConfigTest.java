package com.mimu.simple.sk;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * author: mimu
 * date: 2019/5/14
 */
public class SimpleKafkaKafkaClientConfigTest extends CustomKafkaClientConfig {

    @Test
    public void consume() {
        String topic = "kafka-topic-1";
        Properties properties = getConsumePro();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka-topic-1-group-1");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,"kafka-topic-1-group-1-consumer-1");
        SimpleKafkaConsumer consumer = new SimpleKafkaConsumer(properties);
        consumer.consume(topic, SimpleKafkaKafkaClientConfigTest::printInfo);
    }

    @Test
    public void consume1() {
        String topic = "kafka-topic-1";
        Properties properties = getConsumePro();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka-topic-1-group-1");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,"kafka-topic-1-group-1-consumer-1");
        SimpleKafkaConsumer consumer = new SimpleKafkaConsumer(properties);
        List<PartitionInfo> partitionInfos = consumer.getConsumer().partitionsFor(topic);
        List<TopicPartition> topicPartitions = partitionInfos.stream().parallel().map(partitionInfo -> new TopicPartition(partitionInfo.topic(), partitionInfo.partition())).collect(Collectors.toList());
        consumer.consume(topicPartitions, SimpleKafkaKafkaClientConfigTest::printInfo);
    }

    @Test
    public void consumeByHandCommit() {
        String topic = "kafka-topic-1";
        Properties properties = getConsumePro();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka-topic-1-group-1");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,"kafka-topic-1-group-1-consumer-1");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        SimpleKafkaConsumer consumer = new SimpleKafkaConsumer(properties);
        consumer.consumeByHandCommit(topic, SimpleKafkaKafkaClientConfigTest::printInfo);
    }

    @Test
    public void consumeByHandCommit1() {
        String topic = "kafka-topic-1";
        Properties properties = getConsumePro();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka-topic-1-group-1");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,"kafka-topic-1-group-1-consumer-1");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        SimpleKafkaConsumer consumer = new SimpleKafkaConsumer(properties);
        consumer.consumeByHandCommit1(topic, SimpleKafkaKafkaClientConfigTest::printInfo);
    }

    @Test
    public void consumeByHandCommit2() {
        String topic = "kafka-topic-1";
        Properties properties = getConsumePro();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka-topic-1-group-1");
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,"kafka-topic-1-group-1-consumer-1");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        SimpleKafkaConsumer consumer = new SimpleKafkaConsumer(properties);
        consumer.consumeByHandCommit2(topic, SimpleKafkaKafkaClientConfigTest::printInfo);
    }


}