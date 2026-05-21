package com.example.cloudproject.s3;

import lombok.Getter;

@Getter
public class FileUploadResponse {

    private final String key;

    public FileUploadResponse(String key) {
        this.key = key;
    }
}
