package com.example.cloudproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveMemberRequest {

    private String name;
    private Long age;
    private String mbti;
}
