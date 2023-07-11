package com.ricky.jpa.jpabook.repository;

import com.ricky.jpa.jpabook.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /** 회원 존재 여부 */
    @Query(value = "select * from TB_MEMBER m where name = :name", nativeQuery = true)
    List<Member> findByName(@Param(value = "name") String name);


    @Query(value = "select * from TB_MEMBER m where member_id = :memberId", nativeQuery = true)
    Member findOne(@Param("memberId") Long memberId);

    Optional<Member> findById(Long id);

    List<Member> findAll();

}
