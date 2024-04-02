package com.jh.awslocalstack.sqs;

//import io.awspring.cloud.sqs.annotation.SqsListener;
import com.jh.awslocalstack.model.CustomUserSqsMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyQueueListener {

    private static final Logger log = LoggerFactory.getLogger(MyQueueListener.class);

    @SqsListener("${aws.queue}")
    public void listen(CustomUserSqsMessage message){
        log.info("Received: {}", message.toString());
    }
}
