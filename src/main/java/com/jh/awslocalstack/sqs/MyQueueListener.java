package com.jh.awslocalstack.sqs;

//import io.awspring.cloud.sqs.annotation.SqsListener;
import com.jh.awslocalstack.model.CustomUser;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyQueueListener {

    private static final Logger log = LoggerFactory.getLogger(MyQueueListener.class);

    @SqsListener("${aws.queue.name}")
    public void listen(CustomUser message){
        log.info("Received: ", message.toString());
    }
}
