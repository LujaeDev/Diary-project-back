package com.example.pproject.member.application;

import com.example.pproject.member.domain.Member;
import com.example.pproject.member.dto.MemberDto;
import com.example.pproject.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public void update(Long memberId, MemberDto updateParam) {
        memberRepository.update(memberId, updateParam);
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }


}
