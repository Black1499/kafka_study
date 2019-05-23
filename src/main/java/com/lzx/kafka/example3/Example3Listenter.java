package com.lzx.kafka.example3;

import com.lzx.kafka.entity.Bar;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Example3Listenter {

    @KafkaListener(topics = "ansyc")
    public void listenAnsyc(Bar bar) {
        System.out.println(bar);
    }

    @KafkaListener(topics = "sync")
    public void listenSync(Bar bar) {
        System.out.println(bar);
    }
}