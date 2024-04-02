package com.jh.awslocalstack.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh.awslocalstack.model.CustomUserSqsMessage;
import io.awspring.cloud.sqs.operations.SendResult;
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

    @Value("${aws.queue}")
    private String queueName;

    public void sendMessage(){

        ObjectMapper objectMapper = new ObjectMapper();

        long timeMillis = System.currentTimeMillis();
        CustomUserSqsMessage customUser = new CustomUserSqsMessage(timeMillis, "User_"+timeMillis);


        SendResult result = sqsTemplate.send(to -> to.queue(queueName)
                .payload( customUser));


        log.info("Message: {} sent to queue {}", customUser, queueName);
    }
}
