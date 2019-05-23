package com.lzx.kafka.example3;

import com.lzx.kafka.entity.Bar;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Example3Controller {
    private final SendService sendService;

    @PostMapping("/ansyc")
    public void sendAnsyc(Bar bar){
        sendService.sendAnsyc(bar);
    }

    @PostMapping("/sync")
    public void sendSync(Bar bar){
        sendService.sendSync(bar);
    }
}
