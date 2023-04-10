package com.choimory.memberapi.member.repository;

import com.choimory.memberapi.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByIdentityEquals(String identity);
}
