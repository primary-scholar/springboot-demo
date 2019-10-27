package com.mimu.simple.comn.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * author: mimu
 * date: 2019/5/14
 */
public class SimpleKafkaProducerTest extends CustomKafkaClientConfig {
    private static final Logger logger = LoggerFactory.getLogger(SimpleKafkaProducerTest.class);

    @Test
    public void send() {
        Properties properties = getProducePro();
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "kafka-topic-1-producer-1");
        SimpleKafkaProducer producer = SimpleKafkaProducer.util(properties);
        String topic = "kafka-topic-1", value = "message to kafka cluster";
        producer.send(topic, value);
        producer.close();
    }

    @Test
    public void send1() {
        Properties properties = getProducePro();
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "kafka-topic-1-producer-1");
        SimpleKafkaProducer producer = SimpleKafkaProducer.util(properties);
        String topic = "kafka-topic-1", key = "kafka-topic-1-key-1", value = "message to kafka cluster";
        producer.send(topic, key, value);
        producer.close();
    }

    @Test
    public void send2() {
        Properties properties = getProducePro();
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "kafka-topic-1-producer-1");
        SimpleKafkaProducer producer = SimpleKafkaProducer.util(properties);
        String topic = "kafka-topic-1", value = "message to kafka cluster";
        producer.send(topic, value, (metadata, exception) -> {
            if (exception != null) {
                logger.error("send error topic={},value={}", topic, value, exception);
            } else {
                System.out.println("medata info: " + metadata.topic() + ": " + metadata.partition() + ": "
                        + metadata.offset() + ": " + metadata.timestamp());
            }
        });
        producer.close();
    }

    @Test
    public void send3() {
        Properties properties = getProducePro();
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "kafka-topic-1-producer-1");
        SimpleKafkaProducer producer = SimpleKafkaProducer.util(properties);
        String topic = "kafka-topic-1", key = "kafka-topic-1-key-1", value = "message to kafka cluster";
        producer.send(topic, key, value, (metadata, exception) -> {
            if (exception != null) {
                logger.error("send error topic={},value={}", topic, value, exception);
            } else {
                System.out.println("medata info: " + metadata.topic() + ": " + metadata.partition() + ": "
                        + metadata.offset() + ": " + metadata.timestamp());
            }
        });
        producer.close();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean isRuning = new AtomicBoolean(true);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> isRuning.set(false)));
        Properties properties = new SimpleKafkaProducerTest().getProducePro();
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "kafka-topic-1-producer-1");
        SimpleKafkaProducer producer = SimpleKafkaProducer.util(properties);
        String topic = "kafka_topic_2", value = "message to kafka cluster";
        while (isRuning.get()) {
            producer.send(topic, value, (metadata, exception) -> {
                if (exception != null) {
                    logger.error("send error topic={},value={}", topic, value, exception);
                } else {
                    System.out.println("medata info: " + metadata.topic() + ": " + metadata.partition() + ": "
                            + metadata.offset() + ": " + metadata.timestamp());
                }
            });
            Thread.sleep(500);
        }
        producer.close();
    }

}