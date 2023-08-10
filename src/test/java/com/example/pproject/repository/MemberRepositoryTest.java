package com.example.pproject.repository;

import com.example.pproject.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void before(){
        memberRepository.clear();
    }
    @Test
    void saveTest(){
        Member member = new Member("000803-3070913", "you jae", "010-5133-2895");

        Member savedMember = memberRepository.save(member);
        Member foundMember = memberRepository.findByMemberId(member.getMemberSN());

        Assertions.assertThat(savedMember).isEqualTo(foundMember);
    }
}