package com.example.cloudproject.service;

import com.example.cloudproject.apiResponse.exception.NotFoundException;
import com.example.cloudproject.dto.GetMemberResponse;
import com.example.cloudproject.dto.SaveMemberRequest;
import com.example.cloudproject.dto.SaveMemberResponse;
import com.example.cloudproject.entity.Member;
import com.example.cloudproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public SaveMemberResponse save(SaveMemberRequest request) {
        Member member = new Member(
                request.getName(),
                request.getAge(),
                request.getMbti()
        );

        Member saveMember = memberRepository.save(member);

        return new SaveMemberResponse(
                saveMember.getId(),
                saveMember.getName(),
                saveMember.getAge(),
                saveMember.getMbti()
        );
    }

    @Transactional(readOnly = true)
    public GetMemberResponse findMember(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 팀원이 없습니다."));

        return new GetMemberResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getMbti()
        );
    }
}
