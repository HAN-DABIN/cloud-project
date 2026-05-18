package com.example.cloudproject.controller;

import com.example.cloudproject.apiResponse.ApiResponse;
import com.example.cloudproject.dto.GetMemberResponse;
import com.example.cloudproject.dto.SaveMemberRequest;
import com.example.cloudproject.dto.SaveMemberResponse;
import com.example.cloudproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<SaveMemberResponse>> saveMember(
            @RequestBody SaveMemberRequest request) {

        log.info("[API-LOG] 팀원 저장 요청");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        HttpStatus.CREATED,
                        "팀원 정보 저장 성공",
                        memberService.save(request)
                ));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<GetMemberResponse>> getMember(
            @PathVariable Long memberId) {

        log.info("[API-LOG] 팀원 조회 요청");

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        HttpStatus.OK,
                        "팀원 조회 성공",
                        memberService.findMember(memberId)
                ));
    }
}

