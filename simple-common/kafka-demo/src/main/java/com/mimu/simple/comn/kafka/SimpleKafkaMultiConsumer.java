package com.mimu.simple.comn.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 * author: mimu
 * date: 2019/5/14
 */
public class SimpleKafkaMultiConsumer {
    private static List<KafkaMultiConsumerRunnable> kafkaMultiConsumerRunnables = new ArrayList<>();
    private static List<KafkaSingleConsumerRunnable> kafkaSingleConsumerRunnables = new ArrayList<>();
    private static KafkaSingleConsumerRunnable kafkaSingleConsumerRunnable;

    public static SimpleKafkaMultiConsumer getMultiConsumer(int threads, Properties properties, String topic, Function function) {
        for (int i = 0; i < threads; i++) {
            kafkaMultiConsumerRunnables.add(new KafkaMultiConsumerRunnable(properties, topic, function));
        }
        return new SimpleKafkaMultiConsumer();
    }

    public static SimpleKafkaMultiConsumer getMultiSingleConsumer(int threads, Properties properties, String topic, Function function) {
        for (int i = 0; i < threads; i++) {
            kafkaSingleConsumerRunnables.add(new KafkaSingleConsumerRunnable(threads, properties, topic, function));
        }
        return new SimpleKafkaMultiConsumer();
    }

    public static SimpleKafkaMultiConsumer getSingleConsumer(int threads, Properties properties, String topic, Function function) {
        kafkaSingleConsumerRunnable = new KafkaSingleConsumerRunnable(threads, properties, topic, function);
        return new SimpleKafkaMultiConsumer();
    }

    public void multiConsumerConsume() {
        for (KafkaMultiConsumerRunnable runnable : kafkaMultiConsumerRunnables) {
            new Thread(runnable).start();
        }
    }

    public void multiSingleConsumerConsume() {
        for (KafkaSingleConsumerRunnable runnable : kafkaSingleConsumerRunnables) {
            new Thread(runnable).start();
        }
    }

    public void singleConsumerConsume() {
        new Thread(kafkaSingleConsumerRunnable).start();
    }

    /**
     * 每个线程 为一个consumer 实例，通常情况下 每个consumer线程 消费topic 的一个分区，即分区数和 线程数相等
     * 该种方式，多个consumer 和一个topic 保持tcp 链接，即集群中每个 topic 都存在 多个consumer 实例与该topic 保持链接
     * 对于某个 kafka 集群 会存在 大量的 tcp 链接
     */
    /**
     * 此中方法 中手动提交 偏移量 比较简单，直接和 单线程中的手动提交偏移量一样
     */
    private static class KafkaMultiConsumerRunnable implements Runnable {
        private final Logger logger = LoggerFactory.getLogger(KafkaMultiConsumerRunnable.class);
        private final AtomicBoolean isConsume = new AtomicBoolean(true);
        private Properties properties;
        private KafkaConsumer<String, Object> consumer;
        private Function function;

        public KafkaMultiConsumerRunnable(Properties pro, String topic, Function function) {
            this.properties = pro;
            consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(Collections.singleton(topic), new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

                }

                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

                }
            });
            this.function = function;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            try {
                while (isConsume.get()) {
                    ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(100));
                    Set<TopicPartition> topicPartitions = records.partitions();
                    for (TopicPartition partition : topicPartitions) {
                        List<ConsumerRecord<String, Object>> partitionRecord = records.records(partition);
                        for (ConsumerRecord record : partitionRecord) {
                            function.apply(record.value());
                            logger.info("Thread info={},topic={},partition={},offset={},value={}", Thread.currentThread().getName()
                                    , record.topic(), record.partition(), record.offset(), record.value());
                        }
                        long offset = partitionRecord.get(partitionRecord.size() - 1).offset();
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
    }

    /**
     * 此方式 只有一个 consumer 实例，该consumer 实例 订阅了 某个 topic 的全部 分区，并在 多线程中 处理拉取的消息
     */
    /**
     * 此中方法的 偏移量提交，存在 消息丢失的风险
     */
    private static class KafkaSingleConsumerRunnable implements Runnable {
        private final Logger logger = LoggerFactory.getLogger(KafkaMultiConsumerRunnable.class);
        final Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        private final AtomicBoolean isConsume = new AtomicBoolean(true);
        private KafkaConsumer<String, Object> consumer;
        private Function function;
        ExecutorService executorService;

        public KafkaSingleConsumerRunnable(int thread, Properties properties, String topic, Function function) {
            executorService = new ThreadPoolExecutor(thread, thread, 0l, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
            this.consumer = new KafkaConsumer<>(properties);
            this.function = function;
            consumer.subscribe(Collections.singleton(topic), new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

                }

                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

                }
            });
        }

        @Override
        public void run() {
            try {
                while (isConsume.get()) {
                    ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(100));
                    if (!records.isEmpty()) {
                        Set<TopicPartition> topicPartitions = records.partitions();
                        for (TopicPartition partition : topicPartitions) {
                            List<ConsumerRecord<String, Object>> partitionRecord = records.records(partition);
                            for (ConsumerRecord<String, Object> record : partitionRecord) {
                                executorService.submit(new RecordHandler(record, function));
                                logger.info("Thread info={},topic={},partition={},offset={},value={}", Thread.currentThread().getName()
                                        , record.topic(), record.partition(), record.offset(), record.value());
                            }
                            long lastOffset = partitionRecord.get(partitionRecord.size() - 1).offset();
                            synchronized (offsets) {
                                if (!offsets.containsKey(partition)) {
                                    offsets.put(partition, new OffsetAndMetadata(lastOffset + 1));
                                } else {
                                    long position = offsets.get(partition).offset();
                                    if (position < lastOffset + 1) {
                                        offsets.put(partition, new OffsetAndMetadata(lastOffset + 1));
                                    }
                                }
                            }
                        }
                        synchronized (offsets) {
                            if (!offsets.isEmpty()) {
                                consumer.commitSync(offsets);
                                offsets.clear();
                            }
                        }
                    }
                }
            } finally {
                consumer.close();
                executorService.shutdown();
            }
        }

        class RecordHandler implements Runnable {
            private final Logger logger = LoggerFactory.getLogger(RecordHandler.class);
            private ConsumerRecord<String, Object> record;
            private Function function;

            private RecordHandler(ConsumerRecord<String, Object> record, Function function) {
                this.record = record;
                this.function = function;
            }

            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                function.apply(record.value());
                logger.info("Thread info={},topic={},partition={},offset={},value={}", Thread.currentThread().getName()
                        , record.topic(), record.partition(), record.offset(), record.value());
            }
        }
    }

}
