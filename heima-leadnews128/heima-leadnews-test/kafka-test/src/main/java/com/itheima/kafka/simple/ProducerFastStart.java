package com.itheima.kafka.simple;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 消息生产者代码
 * @作者 itcast
 * @创建日期 2021/7/9 9:42
 **/
public class ProducerFastStart {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // kafka生产者的配置
        Properties config = new Properties();
        // kafka 服务端地址
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.200.130:9092");

        // key 的序列
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // value 的序列
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        config.put(ProducerConfig.RETRIES_CONFIG,10);
        // kafka生产者对象
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(config);


        // 消息对象
        ProducerRecord<String,String> record = new ProducerRecord<String,String>("heima-001","message-key001","hello 你好");


//        // 发送消息   同步发送
//        //           生产者确认机制:   0:    1   all
//        //           自动重试发送机制
//        RecordMetadata recordMetadata = kafkaProducer.send(record).get();
//        System.out.println("分区: " + recordMetadata.partition()  + "    偏移量: " + recordMetadata.offset());


        // 效率比同步高, 没有重试
        kafkaProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e!=null){
                    System.out.println("消息发送失败");
                }
                System.out.println("分区: " + recordMetadata.partition()  + "    偏移量: " + recordMetadata.offset());
            }
        });
        System.out.println("检查是否异步发送");
        // 关闭
        kafkaProducer.close();

    }

}
