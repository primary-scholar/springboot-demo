package com.mimu.simple.sk;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 * author: mimu
 * date: 2019/5/14
 */
public class SimpleKafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleKafkaConsumer.class);
    private AtomicBoolean isConsume = new AtomicBoolean(true);
    private KafkaConsumer<String, Object> consumer;
    private Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();

    public SimpleKafkaConsumer(Properties pro) {
        consumer = new KafkaConsumer<>(pro);
    }

    public KafkaConsumer<String, Object> getConsumer() {
        return consumer;
    }

    @SuppressWarnings("unchecked")
    public void consume(String topic, Function function) {
        consumer.subscribe(Collections.singleton(topic));
        try {
            while (isConsume.get()) {
                ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(500));
                for (ConsumerRecord record : records) {
                    function.apply(record.value());
                    logger.info("Thread info={},topic={},partition={},offset={},value={}", Thread.currentThread().getName()
                            , record.topic(), record.partition(), record.offset(), record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }

    @SuppressWarnings("unchecked")
    public void consume(List<TopicPartition> topicPartitionList, Function function) {
        consumer.assign(topicPartitionList);
        try {
            while (isConsume.get()) {
                ConsumerRecords records = consumer.poll(Duration.ofMillis(5));
                Set<TopicPartition> topicPartitions = records.partitions();
                for (TopicPartition partition : topicPartitions) {
                    List<ConsumerRecord> list = records.records(partition);
                    for (ConsumerRecord record : list) {
                        function.apply(record.value());
                        logger.info("Thread info={},topic={},partition={},offset={},value={}", Thread.currentThread().getName()
                                , record.topic(), record.partition(), record.offset(), record.value());
                    }
                }
            }
        } finally {
            consumer.close();
        }
    }


    /**
     * 需设置 ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG="false"
     *
     * @param topic
     * @param function
     */
    @SuppressWarnings("unchecked")
    public void consumeByHandCommit(String topic, Function function) {
        consumer.subscribe(Collections.singleton(topic), new ConsumerRebalanceListener() {
            /**
             * 该方法 在消费者 在平衡时 停止抓取数据之前调用，所以 应该在 在平衡前 提交消费偏移量，防止重复消费
             * @param partitions
             */
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                // store the offset to db
            }

            /**
             * 该方法 在消费者 在平衡时，重新获取分区后调用，一般是 自定义从某个偏移量出开始 消费
             * @param partitions
             */
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                for (TopicPartition partition : partitions) {
                    consumer.seek(partition, getOffset(partition));
                }
            }

            private long getOffset(TopicPartition topicPartition) {
                // get the offset fro db;
                return 0;
            }
        });
        try {
            while (isConsume.get()) {
                ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(5));
                /*
                消费一条，提交一条，但性能最低,很少使用
                */
                for (ConsumerRecord record : records) {
                    function.apply(record.value());
                    logger.info("Thread info={},topic={},partition={},offset={},value={}", Thread.currentThread().getName()
                            , record.topic(), record.partition(), record.offset(), record.value());
                    long offset = record.offset();
                    TopicPartition partition = new TopicPartition(record.topic(), record.partition());
                    //consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(offset + 1)));
                    /*
                    或使用异步方式提交，或使用异步方式提交，
                    */
                    consumer.commitAsync(Collections.singletonMap(partition, new OffsetAndMetadata(offset + 1)), (offsets, exception) -> {
                        if (exception == null) {
                            logger.info("commitAsync offset={}", offsets);
                        } else {
                            logger.error("commitAsync error offset={}", offsets, exception);
                        }
                    });
                }
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * 需设置 ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG="false"
     *
     * @param topic
     * @param function
     */
    @SuppressWarnings("unchecked")
    public void consumeByHandCommit1(String topic, Function function) {
        consumer.subscribe(Collections.singleton(topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                consumer.commitSync(currentOffset);
                currentOffset.clear();
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                //do nothing
            }
        });
        try {
            while (isConsume.get()) {
                ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(5));
                for (ConsumerRecord record : records) {
                    function.apply(record.value());
                    logger.info("Thread info={},topic={},partition={},offset={},value={}", Thread.currentThread().getName()
                            , record.topic(), record.partition(), record.offset(), record.value());
                    currentOffset.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1));
                }
                /*
                不管是同步提交还是异步提交，提交的都是 poll() 方法返回的最新的拉取offset
                */
                //consumer.commitAsync();
                consumer.commitSync();
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * 需设置 ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG="false"
     *
     * @param topic
     * @param function
     */
    @SuppressWarnings("unchecked")
    public void consumeByHandCommit2(String topic, Function function) {
        try {
            consumer.subscribe(Collections.singleton(topic));
            while (isConsume.get()) {
                ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(5));
                Set<TopicPartition> topicPartitions = records.partitions();
                for (TopicPartition partition : topicPartitions) {
                    List<ConsumerRecord<String, Object>> partitionRecord = records.records(partition);
                    for (ConsumerRecord record : partitionRecord) {
                        function.apply(record.value());
                        logger.info("Thread info={},topic={},partition={},offset={},value={}", Thread.currentThread().getName()
                                , record.topic(), record.partition(), record.offset(), record.value());
                    }
                    /*
                    按照分区粒度，同步提交消费记录
                    */
                    long offset = partitionRecord.get(partitionRecord.size() - 1).offset();
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(offset + 1)));
                    /*
                    或使用异步方式提交，需设置 ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG="false"
                    */
                    /*consumer.commitAsync(Collections.singletonMap(partition, new OffsetAndMetadata(offset + 1)), (offsets, exception) -> {
                        if (exception == null) {
                            logger.info("commitAsync offset={}", offsets);
                        } else {
                            logger.error("commitAsync error offset={}", offsets, exception);
                        }
                    });*/
                }
            }
        } finally {
            consumer.close();
        }
    }

}
