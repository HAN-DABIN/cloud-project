package com.example.cloudproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMemberResponse {

    private final Long id;
    private final String name;
    private final Long age;
    private final String mbti;
}
