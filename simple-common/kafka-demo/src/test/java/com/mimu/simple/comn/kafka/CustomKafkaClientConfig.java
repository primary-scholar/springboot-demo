package com.mimu.simple.comn.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * author: mimu
 * date: 2019/5/14
 */
public class CustomKafkaClientConfig {

    public Properties getConsumePro() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.3.142:9092");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "50000");
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "60000");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1000");
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "1");
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "3000");
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "1048576");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    public Properties getProducePro() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.3.142:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "-1");
        props.put(ProducerConfig.RETRIES_CONFIG, "1");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, "8192");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "10");
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class.getName());
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, "5000");
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "1048576");
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
        return props;
    }

    public static Object printInfo(Object o) {
        System.out.println("msg: " + o);
        return null;
    }
}
