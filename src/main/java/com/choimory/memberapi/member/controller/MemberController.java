package com.choimory.memberapi.member.controller;

import com.choimory.memberapi.jwt.JwtUtil;
import com.choimory.memberapi.member.data.request.*;
import com.choimory.memberapi.member.data.response.*;
import com.choimory.memberapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    //본인 상세조회
    @GetMapping
    public ResponseMemberFind findMe (@RequestHeader(name = "Authorization") final String token) {
        return memberService.find(jwtUtil.findIdentityFromToken(token));
    }

    //타인 상세조회
    @GetMapping("/{identity}")
    public ResponseMemberFind findOther (@PathVariable final String identity) {
        return memberService.find(identity);
    }

    //가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMemberJoin join (@RequestBody @Valid final RequestMemberJoin param) {
        return memberService.join(param);
    }

    //업데이트
    @PutMapping
    public ResponseMemberUpdate update (@RequestHeader(name = "Authorization") final String token,
                                        @RequestBody @Valid final RequestMemberUpdate param) {
        return memberService.update(jwtUtil.findIdentityFromToken(token), param);
    }

    //로그인
    @PostMapping("/login")
    public ResponseMemberLogin login (@RequestBody @Valid final RequestMemberLogin param) {
        return memberService.login(param);
    }

    //밴
    @DeleteMapping("/{targetIdentity}")
    public ResponseMemberBan ban (@PathVariable final String targetIdentity,
                                      @RequestHeader(name = "Authorization") final String token,
                                      @RequestBody @Valid final RequestMemberBan param) {
        return memberService.ban(targetIdentity, jwtUtil.findIdentityFromToken(token), param);
    }

    //탈퇴
    @DeleteMapping
    public ResponseMemberWithdrawal withdrawal (@RequestHeader(name = "Authorization") final String token,
                                                @RequestBody final RequestMemberWithdrawal param) {
        return memberService.withdrawal(jwtUtil.findIdentityFromToken(token));
    }
}
