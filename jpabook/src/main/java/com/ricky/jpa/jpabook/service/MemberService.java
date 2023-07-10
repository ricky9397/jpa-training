package com.ricky.jpa.jpabook.service;

import com.ricky.jpa.jpabook.domain.Member;
import com.ricky.jpa.jpabook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param member
     * @return getId();
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 중복 체크
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Optional<Member> findById(long memberId) {
        return Optional.ofNullable(memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("사용자가 존재하지 않습니다.")));
    }


    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}


