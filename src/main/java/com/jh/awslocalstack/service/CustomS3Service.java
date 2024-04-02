package com.jh.awslocalstack.service;

import io.awspring.cloud.s3.S3Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CustomS3Service {
    private static final Logger log = LoggerFactory.getLogger(CustomS3Service.class);
    private final S3Template s3Template;
    @Value("${aws.bucket}")
    private String bucketName;

    public CustomS3Service (S3Template s3Template) {
        this.s3Template = s3Template;
    }
    public List<String> getBucketsWithTemplate(){
        return s3Template.listObjects(bucketName, "").stream()
                .map(s3Resource -> s3Resource.getFilename())
                .toList();
    }
    public void uploadFile(@RequestParam MultipartFile file) throws IOException {
        s3Template.upload(bucketName, file.getOriginalFilename(), file.getInputStream());
    }
}