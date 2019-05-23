package com.lzx.kafka.example2;

import com.lzx.kafka.entity.Bar;
import com.lzx.kafka.entity.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "handler", topics = {"foo", "bar"})
public class ListenHandler {
    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @KafkaHandler
    public void foo(@Payload Foo foo, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        System.out.println("key:" + key);
        System.out.println("foo:" + foo.toString());
    }

    @KafkaHandler
    public void foo(Bar bar) {
        System.out.println("bar:" + bar.toString());
        kafkaTemplate.send("ansyc", bar);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println(object.toString());
    }
}
