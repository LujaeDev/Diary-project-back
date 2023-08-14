package com.example.pproject.member.domain;

import com.example.pproject.member.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByMemberId(Long id);

    Member update(Long id, MemberDto dto);

    List<Member> findAll();
}
