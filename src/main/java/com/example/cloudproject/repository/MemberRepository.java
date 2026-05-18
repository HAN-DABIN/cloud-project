package com.example.cloudproject.repository;


import com.example.cloudproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
