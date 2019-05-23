package com.lzx.kafka.example3;

import com.lzx.kafka.entity.Bar;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class SendService {

    private final KafkaTemplate<Object, Object> template;

    // 异步
    public void sendAnsyc(final Bar bar) {
//        ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>("ansyc", bar);

        ListenableFuture<SendResult<Object, Object>> future = template.send("ansyc",bar);
        future.addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                System.out.println("发送消息成功：" + result);
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败："+ ex.getMessage());
            }
        });
    }

    // 同步
    public void sendSync(final Bar bar) {
        ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>("sync", bar);
        try {
            template.send(producerRecord).get(10, TimeUnit.SECONDS);
            System.out.println("发送成功");
        }
        catch (ExecutionException e) {
            System.out.println("发送消息失败："+ e.getMessage());
        }
        catch (TimeoutException | InterruptedException e) {
            System.out.println("发送消息失败："+ e.getMessage());
        }
    }
}
