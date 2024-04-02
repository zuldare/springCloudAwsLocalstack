package com.jh.awslocalstack;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@SpringBootApplication
public class AwslocalstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwslocalstackApplication.class, args);
	}


}
