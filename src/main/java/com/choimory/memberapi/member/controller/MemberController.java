package com.choimory.memberapi.member.controller;

import com.choimory.memberapi.member.data.request.*;
import com.choimory.memberapi.member.data.response.*;
import com.choimory.memberapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Validated
public class MemberController {
    private final MemberService memberService;

    //본인 상세조회
    @GetMapping
    public ResponseMemberFind findMe (@RequestHeader(name = "Authorization") final String token) {
        //TODO token to ID
        return memberService.find(1L);
    }

    //타인 상세조회
    @GetMapping("/{id}")
    public ResponseMemberFind findOther (@PathVariable @Min(1) @Valid final Long id) {
        return memberService.find(id);
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
        //TODO token to ID
        return memberService.update(1L, param);
    }

    //로그인
    @PostMapping("/login")
    public ResponseMemberLogin login (@RequestBody @Valid final RequestMemberLogin param) {
        return memberService.login(param);
    }

    //밴
    @DeleteMapping("/{targetId}")
    public ResponseMemberBan ban (@PathVariable @Min(1) @Valid final Long targetId,
                                      @RequestHeader(name = "Authorization") final String token,
                                      @RequestBody @Valid final RequestMemberBan param) {
        //TODO token to my ID
        return memberService.ban(targetId, 1L, param);
    }

    //탈퇴
    @DeleteMapping
    public ResponseMemberWithdrawal withdrawal (@RequestHeader(name = "Authorization") final String token,
                                                @RequestBody final RequestMemberWithdrawal param) {
        // TODO token to ID
        return memberService.withdrawal(1L, param);
    }
}
