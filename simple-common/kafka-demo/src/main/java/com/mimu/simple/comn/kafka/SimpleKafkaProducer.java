package com.mimu.simple.comn.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author: mimu
 * date: 2019/5/14
 */
public class SimpleKafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleKafkaProducer.class);
    private static final ReentrantReadWriteLock.WriteLock lock = new ReentrantReadWriteLock().writeLock();
    private KafkaProducer<String, Object> producer;
    private static volatile SimpleKafkaProducer simpleKafkaProducer;

    public static SimpleKafkaProducer util(Properties pro) {
        if (simpleKafkaProducer == null) {
            synchronized (lock) {
                if (simpleKafkaProducer == null) {
                    simpleKafkaProducer = new SimpleKafkaProducer(pro);
                }
            }
        }
        return simpleKafkaProducer;
    }

    private SimpleKafkaProducer(Properties pro) {
        producer = new KafkaProducer<>(pro);
    }

    public void send(String topic, Object value) {
        send(topic, null, value, null);
    }

    public void send(String topic, String key, Object value) {
        send(topic, key, value, null);
    }

    public void send(String topic, Object value, Callback callback) {
        send(topic, null, value, callback);
    }

    @SuppressWarnings("unchecked")
    public void send(String topic, String key, Object value, Callback callback) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, key, value);
        try {
            producer.send(record, callback);
        } catch (Exception e) {
            logger.error("SimpleKafkaUtil send error topic={},key={},value={}", e);
        }
    }

    public void close() {
        logger.error("SimpleKafkaUtil producer close");
        producer.close();
    }
}
