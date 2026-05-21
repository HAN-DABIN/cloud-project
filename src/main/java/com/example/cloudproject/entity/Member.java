package com.example.cloudproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long age;
    private String mbti;
    private String profileImageKey;

    public Member(String name, Long age, String mbti) {
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }

    public void updateProfileImageKey(String key) {
        this.profileImageKey = key;
    }
}
