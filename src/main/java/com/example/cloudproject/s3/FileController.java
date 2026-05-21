package com.example.cloudproject.s3;

import com.example.cloudproject.apiResponse.ApiResponse;
import com.example.cloudproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("/api/members/{id}/profile-image")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final S3Service s3Service;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<FileUploadResponse>> upload(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        String key = s3Service.upload(file);

        memberService.updateProfileImage(id, key);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        HttpStatus.CREATED,
                        "이미지 업로드 성공",
                        new FileUploadResponse(key)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<FileDownloadUrlResponse>> getDownloadUrl(
            @PathVariable Long id){

        String key = memberService.getProfileImageKey(id);

        URL url = s3Service.getDownloadUrl(key);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        HttpStatus.OK,
                        "파일 다운로드 성공",
                        new FileDownloadUrlResponse(url.toString())
                ));
    }
}