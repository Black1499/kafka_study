package com.lzx.kafka.example2;

import com.lzx.kafka.entity.Bar;
import com.lzx.kafka.entity.Foo;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Example2Controller {

    private final KafkaTemplate kafkaTemplate;

    @PostMapping("/foo")
    public void send(Foo foo){
        kafkaTemplate.send("foo", "modelOne", foo);
    }

    @PostMapping("/bar")
    public void send(Bar bar){
        kafkaTemplate.send("bar", bar);
    }

    @GetMapping("/what/{what}")
    public void send(@PathVariable String what){
        kafkaTemplate.send("what", what);
    }
}
