package com.example.pproject.repository;

import com.example.pproject.member.domain.Member;
import com.example.pproject.member.domain.MemberRepositoryV1;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MemberRepositoryTest {
    @Autowired
    MemberRepositoryV1 memberRepository;

    @BeforeEach
    void before(){
        memberRepository.clear();
    }
    @Test
    void saveTest(){
        Member member = new Member("you jae", "Lee");

        Member savedMember = memberRepository.save(member);
        log.info("memberId = {}", savedMember.getMemberId());
        Member foundMember = memberRepository.findById(member.getMemberId()).get();

        Assertions.assertThat(savedMember).isEqualTo(foundMember);
    }
}