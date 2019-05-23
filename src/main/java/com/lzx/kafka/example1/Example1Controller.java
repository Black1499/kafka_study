package com.lzx.kafka.example1;

import com.lzx.kafka.entity.Foo;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Example1Controller {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @PostMapping("/send/foo")
    public void sendFoo(Foo foo) {
        kafkaTemplate.executeInTransaction(kafkaTemplate -> {
            kafkaTemplate.send("foo", foo);
            return true;
        });
    }
}
