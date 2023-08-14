package com.example.pproject.member.domain;

import com.example.pproject.member.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);


    List<Member> findAll();
}
