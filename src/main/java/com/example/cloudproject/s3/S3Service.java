package com.example.cloudproject.s3;

import com.example.cloudproject.apiResponse.exception.FileUploadException;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7);

    private final S3Template s3Template;

    @Value("${s3.bucket}")
    private String bucket;

    public String upload(MultipartFile file) {

        log.info("[API - LOG] S3 파일 업로드");

        try {
            String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

            s3Template.upload(bucket, key, file.getInputStream());

            return key;

        } catch (IOException e) {

            log.error("[ERROR] 파일 업로드 실패", e);

            throw new FileUploadException("파일 업로드 실패");
        }
    }

    public URL getDownloadUrl(String key) {
        return s3Template.createSignedGetURL(bucket, key, PRESIGNED_URL_EXPIRATION);
    }
}
