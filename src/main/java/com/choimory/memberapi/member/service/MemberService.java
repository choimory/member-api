package com.choimory.memberapi.member.service;

import com.choimory.memberapi.member.data.request.*;
import com.choimory.memberapi.member.data.response.*;
import com.choimory.memberapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    //상세조회
    public ResponseMemberFind find (final Long id) {
        return null;
    }

    //가입
    public ResponseMemberJoin join (final RequestMemberJoin param) {
        return null;
    }

    //업데이트
    public ResponseMemberUpdate update (final Long id, final RequestMemberUpdate param) {
        return null;
    }

    //로그인
    public ResponseMemberLogin login (final RequestMemberLogin param) {
        return null;
    }

    //밴
    public ResponseMemberBan ban (final Long targetId, final Long myId, final RequestMemberBan param) {
        return null;
    }

    //탈퇴
    public ResponseMemberWithdrawal withdrawal (final Long id, final RequestMemberWithdrawal param) {
        return null;
    }
}
