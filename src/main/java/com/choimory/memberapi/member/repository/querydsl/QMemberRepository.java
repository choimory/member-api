package com.choimory.memberapi.member.repository.querydsl;

import com.choimory.memberapi.member.data.dto.MemberDto;

public interface QMemberRepository {
    boolean existsByIdentityAndPasswordEquals(String identity, String password);
    boolean existsByIdentityOrEmail(String identity, String email);
}
