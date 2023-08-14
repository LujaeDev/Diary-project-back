package com.example.pproject.member.domain;

import com.example.pproject.member.dto.MemberDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository{
    private final SpringMemberRepository repository;

    @Override
    public Member save(Member member) {
        return repository.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
