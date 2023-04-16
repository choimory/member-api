package com.choimory.memberapi.member.data.response;

import com.choimory.memberapi.member.data.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class ResponseMemberFind extends RepresentationModel<ResponseMemberFind> {
    private final MemberDto member;

    @Builder
    public ResponseMemberFind(MemberDto member) {
        this.member = member;
    }
}
