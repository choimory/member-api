package com.choimory.memberapi.member.service;

import com.choimory.memberapi.member.data.dto.MemberDto;
import com.choimory.memberapi.member.data.request.*;
import com.choimory.memberapi.member.data.response.*;
import com.choimory.memberapi.member.entity.Member;
import com.choimory.memberapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    //상세조회
    public ResponseMemberFind find (final String identity) {
        // 아이디로 조회
        Member member = memberRepository.findMemberByIdentityEquals(identity)
                .orElseThrow(() -> new NoSuchElementException("없엉"));

        // DTO 변환
        // 반환
        return ResponseMemberFind.builder()
                .member(MemberDto.toDto(member))
                .build();
    }

    //가입
    public ResponseMemberJoin join (final RequestMemberJoin param) {
        // 썸네일 생성
        // S3 업로드
        // entity 생성
        // db, file commit or rollback
        // DTO 변환
        // 반환
        return null;
    }

    //업데이트
    public ResponseMemberUpdate update (final String identity, final RequestMemberUpdate param) {
        // 썸네일 생성
        // S3 업로드
        // entity 생성
        // db, file commit or rollback
        // DTO 변환
        // 반환
        return null;
    }

    //로그인
    public ResponseMemberLogin login (final RequestMemberLogin param) {
        // 아이디 & 비밀번호로 조회하여 check exists
        // 정지여부 확인
            //정지시 정지에 해당하는 처리
        // 토큰 생성
        // 토큰 반환
        return null;
    }

    //밴
    public ResponseMemberBan ban (final String targetIdentity, final String myIdentity, final RequestMemberBan param) {
        // 대상 회원 확인
        // 대상 회원 밴
        // 결과 dto 변환
        // 결과 반환
        return null;
    }

    //탈퇴
    public ResponseMemberWithdrawal withdrawal (final String identity) {
        // 탈퇴 처리
        // 결과 반환
        return null;
    }
}
