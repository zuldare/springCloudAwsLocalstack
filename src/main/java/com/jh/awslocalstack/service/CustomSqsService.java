package com.jh.awslocalstack.service;

import com.jh.awslocalstack.model.CustomUser;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomSqsService {
    private static final Logger log = LoggerFactory.getLogger(CustomSqsService.class);
    private final SqsTemplate sqsTemplate;
    public CustomSqsService (SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    @Value("${aws.queue.name}")
    private String queueName;

    public void sendMessage(){
        long timeMillis = System.currentTimeMillis();
        CustomUser customUser = new CustomUser(timeMillis, "User_"+timeMillis);
        sqsTemplate.send(to -> to.queue(queueName).payload(customUser));
        log.info("Message: {} sent to queue {}", customUser, queueName);
    }
}
