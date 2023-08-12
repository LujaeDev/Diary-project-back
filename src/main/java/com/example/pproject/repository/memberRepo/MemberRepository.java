package com.example.pproject.repository.memberRepo;

import com.example.pproject.domain.Member;
import com.example.pproject.repository.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByMemberId(Long id);

    Member update(Long id, MemberDto dto);

    List<Member> findAll();
}
