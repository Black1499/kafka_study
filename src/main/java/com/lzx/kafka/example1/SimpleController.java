package com.lzx.kafka.example1;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SimpleController {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @GetMapping("/send/{messge}")
    public String send(@PathVariable String messge) {
        kafkaTemplate.send("topic1", "topci1:" + messge);
        kafkaTemplate.send("topic2", "topci2:" + messge);
        return messge;
    }
}
