package com.itheima.kafka.simple;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @作者 itcast
 * @创建日期 2021/7/9 9:49
 **/
public class ConsumerFastStart {
    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.200.130:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group1");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(config);
        kafkaConsumer.subscribe(Arrays.asList("heima-001"));
        while (true){
            // 9
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            try {
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("消息的key==>" + record.key() + "  消息的value==>"+record.value());
                    kafkaConsumer.commitAsync();
                }
            } catch (Exception e) {
                e.printStackTrace();
                kafkaConsumer.commitSync();
            }
        }
    }
}
