package com.jh.awslocalstack.s3;

import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class S3Controller {
    private final S3Template s3Template;

    @Value("${aws.bucket}")
    private String bucketName;

    public S3Controller (S3Template s3Template) {
        this.s3Template = s3Template;
    }
    @GetMapping("/buckets/template")
    public List<String> getBucketsWithTemplate(){
        return s3Template.listObjects(bucketName, "").stream()
                .map(s3Resource -> s3Resource.getFilename())
                .toList();
    }
    @PostMapping("/buckets")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        s3Template.upload(bucketName, file.getOriginalFilename(), file.getInputStream());
        return ResponseEntity.ok("File uploaded" + file.getOriginalFilename() );
    }
}
