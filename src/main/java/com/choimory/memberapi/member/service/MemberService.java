package com.choimory.memberapi.member.service;

import com.choimory.memberapi.common.exception.CommonException;
import com.choimory.memberapi.jwt.JwtUtil;
import com.choimory.memberapi.member.code.MemberValid;
import com.choimory.memberapi.member.data.dto.MemberDto;
import com.choimory.memberapi.member.data.request.RequestMemberBan;
import com.choimory.memberapi.member.data.request.RequestMemberJoin;
import com.choimory.memberapi.member.data.request.RequestMemberLogin;
import com.choimory.memberapi.member.data.request.RequestMemberUpdate;
import com.choimory.memberapi.member.data.response.*;
import com.choimory.memberapi.member.entity.Member;
import com.choimory.memberapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //상세조회
    public ResponseMemberFind find (final String identity) {
        // 아이디로 조회
        Member member = memberRepository.findMemberByIdentityEqualsAndDeletedAtIsNull(identity)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));

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
        // 아이디로 조회
        MemberDto member = MemberDto.toDto(memberRepository.findMemberByIdentityEqualsAndDeletedAtIsNull(param.getIdentity())
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase())));

        // 비밀번호 일치여부 확인
        Boolean isPasswordMatched = param.isPasswordMatched(passwordEncoder, member.getPassword());
        if(!isPasswordMatched) throw new CommonException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());

        // 정지여부 확인
        Boolean isSuspendedMember = member.isSuspendedMember();
        if(isSuspendedMember) throw new CommonException(HttpStatus.FORBIDDEN, MemberValid.CODE_SUSPENDED_MEMBER, MemberValid.MESSAGE_SUSPENDED_MEMBER);

        // 토큰 생성 및 반환
        return ResponseMemberLogin.builder()
                .identity(member.getIdentity())
                .token(jwtUtil.generateToken(member))
                .build();
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
