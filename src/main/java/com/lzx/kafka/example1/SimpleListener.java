package com.lzx.kafka.example1;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {
    @KafkaListener(topics = {"topic1", "topic2"})
    public void listen1(String data) {
        System.out.println(data);
    }
}
