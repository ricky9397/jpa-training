package com.ricky.jpa.jpabook.service;

import com.ricky.jpa.jpabook.domain.Member;
import com.ricky.jpa.jpabook.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WebAppConfiguration
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 테스트")
    public void 회원가입() throws Exception{
        //Given
        Member member = Member.builder()
                .name("kim")
                .build();
        //When
        Long saveId = memberService.join(member);

        //Then
        assertEquals(member, memberRepository.findById(saveId));
    }
    
    @Test
    @DisplayName("회원목록 테스트")
    public void 회원목록() {

        List<Member> members = memberService.findMembers();

        System.out.println(members.toArray());

    }
}