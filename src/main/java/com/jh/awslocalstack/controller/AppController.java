package com.jh.awslocalstack.controller;


import com.jh.awslocalstack.service.CustomS3Service;
import com.jh.awslocalstack.service.CustomSqsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    private final CustomS3Service s3Service;
    private final CustomSqsService sqsService;

    public AppController (CustomS3Service s3Service, CustomSqsService sqsService) {
        this.s3Service = s3Service;
        this.sqsService = sqsService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage() {
        sqsService.sendMessage();
        return ResponseEntity.ok("Message sent to queue");
    }

    @GetMapping("/buckets/template")
    public List<String> getBucketsWithTemplate(){
        return s3Service.getBucketsWithTemplate();
    }
    @PostMapping("/buckets")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        s3Service.uploadFile(file);
        return ResponseEntity.ok("File uploaded" + file.getOriginalFilename() );
    }
}
